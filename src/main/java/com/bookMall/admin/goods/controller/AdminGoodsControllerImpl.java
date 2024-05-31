package com.bookMall.admin.goods.controller;

import com.bookMall.admin.goods.service.AdminGoodsService;
import com.bookMall.common.base.BaseController;
import com.bookMall.goods.vo.GoodsVO;
import com.bookMall.goods.vo.ImageFileVO;
import com.bookMall.member.vo.MemberVO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminGoddsControllerImpl")
@RequestMapping(value = "/admin/goods")
public class AdminGoodsControllerImpl extends BaseController implements AdminGoodsController {

    private static final String CURR_IMAGE_REPO_PATH = "C:\\shopping\\file_repo";
    private final static Logger log = LoggerFactory.getLogger(AdminGoodsControllerImpl.class);
    @Autowired
    private AdminGoodsService adminGoodsService;


    @Override
    @RequestMapping(value = "/adminGoodsMain.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session = request.getSession();
        session.setAttribute("side_menu", "admin_mode");

        String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
        String section = dateMap.get("section");
        String pageNum = dateMap.get("pageNum");
        String beginDate = null, endDate = null;

        String[] tempDate = calcSearchPeriod(fixedSearchPeriod).split(",");
        beginDate = tempDate[0];
        endDate = tempDate[1];
        dateMap.put("beginDate", beginDate);
        dateMap.put("endDate", endDate);

        Map<String, Object> condMap = new HashMap<String, Object>();
        if (section == null) {
            section = "1";
        }
        condMap.put("section", section);
        if (pageNum == null) {
            pageNum = "1";
        }
        condMap.put("pageNum", pageNum);
        condMap.put("beginDate", beginDate);
        condMap.put("endDate", endDate);
        List<GoodsVO> newGoodsList = adminGoodsService.listNewGoods(condMap);
        mav.addObject("newGoodsList", newGoodsList);

        String[] beginDate1 = beginDate.split("-");
        String[] endDate2 = endDate.split("-");

        mav.addObject("beginYear", beginDate1[0]);
        mav.addObject("beginMonth", beginDate1[1]);
        mav.addObject("beginDay", beginDate1[2]);
        mav.addObject("endYear", endDate2[0]);
        mav.addObject("endMonth", endDate2[1]);
        mav.addObject("endDay", endDate2[2]);

        mav.addObject("section", section);
        mav.addObject("pageNum", pageNum);

        return mav;
    }

    @Override
    @RequestMapping(value = "/addNewGoods.do", method = RequestMethod.POST)
    public ResponseEntity addNewGoods(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
        log.info("AdminGoodsControllerImpl addNewGoods 실행");
        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charser=utf-8");
        String imageFileName = null;
        log.info("AdminGoodsControllerImpl addNewGoods imageFileName : " + imageFileName);

        Map newGoodsMap = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();
        log.info("AdminGoodsControllerImpl addNewGoods enu : " + enu);
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("AdminGoodsControllerImpl addNewGoods name : " + name);
            String value = multipartRequest.getParameter(name);
            log.info("AdminGoodsControllerImpl addNewGoods value : " + value);
            newGoodsMap.put(name, value);
        }

        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String regId = memberVO.getMemberId();
        log.info("AdminGoodsControllerImpl addNewGoods regId : " + regId);
        List<ImageFileVO> imageFileList = upload(multipartRequest);
        if (imageFileList != null && imageFileList.size() != 0) {
            for (ImageFileVO imageFileVO : imageFileList) {
                imageFileVO.setRegId(regId);
            }
            newGoodsMap.put("imageFileList", imageFileList);
        }

        String message = null;
        log.info("AdminGoodsControllerImpl addNewGoods message : " + message);
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html;charset=utf-8");
        try {
            int goodsId = adminGoodsService.addNewGoods(newGoodsMap);
            log.info("AdminGoodsControllerImpl addNewGoods goodsId : " + goodsId);

            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    log.info("AdminGoodsControllerImpl addNewGoods imageFileName : " + imageFileName);
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    log.info("AdminGoodsControllerImpl addNewGoods srcFile : " + srcFile);
                    File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + goodsId);
                    log.info("AdminGoodsControllerImpl addNewGoods destDir : " + destDir);
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
                }
            }
            message = "<script>";
            message += " alert('새상품을 추가했습니다.');";
            message += " location.href='" + multipartRequest.getContextPath() + "/admin/goods/addNewGoodsForm.do';";
            message += ("</script>");
        } catch (Exception e) {
            log.info("AdminGoodsControllerImpl addNewGoods Exception : " + e);
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    srcFile.delete();
                }
            }

            message = "<script>";
            message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
            message += " location.href='" + multipartRequest.getContextPath() + "/admin/goods/addNewGoodsForm.do';";
            message += ("</script>");
            e.printStackTrace();
        }
        resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }


    @Override
    @RequestMapping(value = "/modifyGoodsForm.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView modifyGoodsForm(@RequestParam("goodsId") int goodsId, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("AdminGoodsControllerImpl modifyGoodsForm goodsId : " + goodsId);
        String viewName = (String) request.getAttribute("viewName");
        log.info("AdminGoodsControllerImpl modifyGoodsForm viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);
        log.info("AdminGoodsControllerImpl modifyGoodsForm mav : " + mav);
        Map goodsMap = adminGoodsService.goodsDetail(goodsId);
        log.info("AdminGoodsControllerImpl modifyGoodsForm goodsMap : " + goodsMap);
        mav.addObject("goodsMap", goodsMap);
        return mav;
    }

    @Override
    @RequestMapping(value = "/modifyGoodsInfo.do", method = RequestMethod.POST)
    public ResponseEntity modifyGoodsInfo(@RequestParam("goodsId") String goodsId, @RequestParam("attribute") String attribute, @RequestParam("value") String value, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("AdminGoodsControllerImpl modifyGoodsInfo start");
        Map<String, String> goodsMap = new HashMap<String, String>();
        goodsMap.put("goodsId", goodsId);
        goodsMap.put(attribute, value);

        log.info("AdminGoodsControllerImpl modifyGoodsInfo adminGoodsService.modifyGoodsInfo(goodsMap) 전");
        adminGoodsService.modifyGoodsInfo(goodsMap);
        log.info("AdminGoodsControllerImpl modifyGoodsInfo adminGoodsService.modifyGoodsInfo(goodsMap) 후");

        String message = null;
        ResponseEntity resEntity;
        HttpHeaders responseHeader = new HttpHeaders();
        message = "modSuccess";
        resEntity = new ResponseEntity(message, responseHeader, HttpStatus.OK);
        return resEntity;
    }

    @Override
    @RequestMapping(value = "/modifyGoodsImageInfo.do", method = RequestMethod.POST)
    public void modifyGoodsImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {

        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo start");

        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String imageFileName = null;
        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo imageFileName : " + imageFileName);

        Map goodsMap = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();
        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo enu : " + enu);

        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("AdminGoodsControllerImpl modifyGoodsImageInfo name : " + name);
            String value = multipartRequest.getParameter(name);
            log.info("AdminGoodsControllerImpl modifyGoodsImageInfo value : " + value);
            goodsMap.put(name, value);
        }

        HttpSession session = multipartRequest.getSession();
        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo session : " + session);
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo memberVO : " + memberVO.toString());
        String regId = memberVO.getMemberId();
        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo regId : " + regId);
        List<ImageFileVO> imageFileList = null;
        int goodsId = 0;
        int imageId = 0;

        try {

            imageFileList = upload(multipartRequest);
            log.info("AdminGoodsControllerImpl modifyGoodsImageInfo imageFileList : " + imageFileList.toString());
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    goodsId = Integer.parseInt((String) goodsMap.get("goodsId"));
                    log.info("AdminGoodsControllerImpl modifyGoodsImageInfo goodsId : " + goodsId);
                    imageId = Integer.parseInt((String) goodsMap.get("imageId"));
                    log.info("AdminGoodsControllerImpl modifyGoodsImageInfo imageId : " + imageId);
                    imageFileVO.setGoodsId(goodsId);
                    imageFileVO.setImageId(imageId);
                    imageFileVO.setRegId(regId);
                    log.info("AdminGoodsControllerImpl modifyGoodsImageInfo imageFileVO : " + imageFileVO);
                }
                log.info("AdminGoodsControllerImpl modifyGoodsImageInfo adminGoodsService.modifyGoodsImage(imageFileList) 전");
                adminGoodsService.modifyGoodsImage(imageFileList);
                log.info("AdminGoodsControllerImpl modifyGoodsImageInfo adminGoodsService.modifyGoodsImage(imageFileList) 후");

                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    log.info("AdminGoodsControllerImpl modifyGoodsImageInfo imageFileName : " + imageFileName);
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    log.info("AdminGoodsControllerImpl modifyGoodsImageInfo srcFile : " + srcFile);
                    File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + goodsId);
                    log.info("AdminGoodsControllerImpl modifyGoodsImageInfo destDir : " + destDir);
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
                }
            }
        } catch (Exception e) {

            log.info("AdminGoodsControllerImpl modifyGoodsImage Exception : " + e);
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    srcFile.delete();
                }
            }
            e.printStackTrace();
        }

        log.info("AdminGoodsControllerImpl modifyGoodsImageInfo end");
    }

    @Override
    @RequestMapping(value = "/addNewGoodsImage.do", method = RequestMethod.POST)
    public void addNewGoodsImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
        log.info("AdminGoodsControllerImpl addNewGoodsImage start");

        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String imageFileName = null;

        Map goodsMap = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();

        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            goodsMap.put(name, value);
        }

        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String regId = memberVO.getMemberId();

        List<ImageFileVO> imageFileList = null;
        int goodsId = 0;

        try {
            imageFileList = upload(multipartRequest);
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    goodsId = Integer.parseInt((String) goodsMap.get("goodsId"));
                    imageFileVO.setGoodsId(goodsId);
                    imageFileVO.setRegId(regId);
                }

                adminGoodsService.addNewGoodsImage(imageFileList);
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + goodsId);
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
                }
            }
        } catch (Exception e) {
            if (imageFileList != null && imageFileList.size() != 0) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
                    srcFile.delete();
                }
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    @RequestMapping(value = "/removeGoodsImage.do", method = RequestMethod.POST)
    public void removeGoodsImage(@RequestParam("goodsId") int goodsId, @RequestParam("imageId") int imageId, @RequestParam("imageFileName") String imageFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("AdminGoodsControllerImpl removeGoodsImage start");
        log.info("AdminGoodsControllerImpl goodsId : " + goodsId);
        log.info("AdminGoodsControllerImpl imageId : " + imageId);
        log.info("AdminGoodsControllerImpl imageFileName : " + imageFileName);

        // 여기서 imageId를 올바르게 전달하는지 확인합니다.
        if (goodsId == imageId) {
            throw new IllegalArgumentException("imageId와 goodsId는 같을 수 없습니다.");
        }

        log.info("adminGoodsService.removeGoodsImage(imageId) 전");
        adminGoodsService.removeGoodsImage(imageId); // 올바른 imageId를 전달합니다.
        log.info("adminGoodsService.removeGoodsImage(imageId) 후");
        try {
            File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + goodsId + "\\" + imageFileName);
            srcFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
