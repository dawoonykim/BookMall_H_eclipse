package com.bookMall.mypage.controller;

import com.bookMall.common.base.BaseController;
import com.bookMall.member.vo.MemberVO;
import com.bookMall.mypage.service.MyPageService;
import com.bookMall.order.vo.OrderVO;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("myPageControllerImpl")
@RequestMapping("/mypage")
public class MyPageControllerImpl extends BaseController implements MyPageController {

    private static final Logger log = LoggerFactory.getLogger(MyPageController.class);
    @Autowired
    private MyPageService myPageService;
    @Autowired
    private MemberVO memberVO;

    @Override
    @RequestMapping(value = "/myPageMain.do", method = RequestMethod.GET)
    public ModelAndView myPageMain(@RequestParam(required = false, value = "message") String message, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("side_menu", "my_page");
        String viewName = (String) request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        memberVO = (MemberVO) session.getAttribute("memberInfo");
        String member_id = memberVO.getMemberId();
        List<OrderVO> myOrderList = myPageService.listMyOrderGoods(member_id);

        mav.addObject("message", message);
        mav.addObject("myOrderList", myOrderList);
        return mav;
    }

    @Override
    @RequestMapping(value = "/cancelMyOrder.do", method = RequestMethod.POST)
    public ModelAndView cancelMyOrder(@RequestParam("orderId") String order_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        myPageService.cancelOrder(order_id);
        mav.addObject("message", "cancel_order");
        mav.setViewName("redirect:/mypage/myPageMain.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/myDetailInfo.do", method = RequestMethod.GET)
    public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("MyPageController myDetailInfo GET");
        String viewName = (String) request.getAttribute("viewName");
        log.info("MyPageController myDetailInfo viewName : " + viewName);
        ModelAndView mav = new ModelAndView(viewName);
        log.info("MyPageController myDetailInfo mav : " + mav);
        return mav;
    }

    @Override
    @RequestMapping(value = "/modifyMyInfo.do", method = RequestMethod.POST)
    public ResponseEntity modifyMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("MyPageControllerImpl modifyMyInfo Post");
        Map<String, String> memberMap = new HashMap<String, String>();
        log.info("MyPageControllerImpl modifyMyInfo memberMap : " + memberMap.toString());
        String val[] = null;
        HttpSession session = request.getSession();
        memberVO = (MemberVO) session.getAttribute("memberInfo");
        String member_id = memberVO.getMemberId();
        log.info("MyPageControllerImpl modifyMyInfo member_id : " + member_id);
        if (attribute.equals("memberBirth")) {
            log.info("MyPageControllerImpl modifyMyInfo memberBirth");
            val = value.split(",");
            log.info("MyPageControllerImpl modifyMyInfo val : "+val);
            memberMap.put("memberBirthY", val[0]);
            memberMap.put("memberBirthM", val[1]);
            memberMap.put("memberBirthD", val[2]);
            memberMap.put("memberBirthGn", val[3]);
            log.info("MyPageControllerImpl modifyMyInfo memberMap : "+memberMap);
        } else if (attribute.equals("tel")) {
            log.info("MyPageControllerImpl modifyMyInfo tel");
            val = value.split(",");
            memberMap.put("tel1", val[0]);
            memberMap.put("tel2", val[1]);
            memberMap.put("tel3", val[2]);
            log.info("MyPageControllerImpl modifyMyInfo memberMap : "+memberMap);
        } else if (attribute.equals("hp")) {
            log.info("MyPageControllerImpl modifyMyInfo hp");
            val = value.split(",");
            memberMap.put("hp1", val[0]);
            memberMap.put("hp2", val[1]);
            memberMap.put("hp3", val[2]);
            memberMap.put("smsStsYn", val[3]);
            log.info("MyPageControllerImpl modifyMyInfo memberMap : "+memberMap);
        } else if (attribute.equals("email")) {
            log.info("MyPageControllerImpl modifyMyInfo email");
            val = value.split(",");
            memberMap.put("email1", val[0]);
            memberMap.put("email2", val[1]);
            memberMap.put("emailStsYn", val[2]);
            log.info("MyPageControllerImpl modifyMyInfo memberMap : "+memberMap);
        } else if (attribute.equals("address")) {
            val = value.split(",");
            log.info("MyPageControllerImpl modifyMyInfo address");
            memberMap.put("zipCode", val[0]);
            memberMap.put("roadAddress", val[1]);
            memberMap.put("jibunAddress", val[2]);
            memberMap.put("namujiAddress", val[3]);
            log.info("MyPageControllerImpl modifyMyInfo memberMap : "+memberMap);
        } else {
            log.info("MyPageControllerImpl modifyMyInfo else");
            log.info("MyPageControllerImpl modifyMyInfo attribute : " + attribute + " value : " + value);
            memberMap.put(attribute, value);
        }
        memberMap.put("memberId", member_id);
        log.info("MyPageControllerImpl modifyMyInfo memberMap : " + memberMap.toString());
        memberVO = myPageService.modifyMyInfo(memberMap);
        log.info("MyPageControllerImpl modifyMyInfo memberVO : " + memberMap.toString());
        session.removeAttribute("memberInfo");
        session.setAttribute("memberInfo", memberVO);

        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        log.info("MyPageControllerImpl modifyMyInfo responseHeaders : " + responseHeaders);
        message = "modSuccess";
        log.info("MyPageControllerImpl modifyMyInfo message : " + message);

        resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        log.info("MyPageControllerImpl modifyMyInfo resEntity : " + resEntity);
        return resEntity;
    }


}