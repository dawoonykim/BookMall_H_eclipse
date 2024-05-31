package com.bookMall.goods.controller;

import com.bookMall.common.base.BaseController;
import com.bookMall.goods.service.GoodsService;
import com.bookMall.goods.vo.GoodsVO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("goodsControllerImpl")
@RequestMapping(value = "/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsControllerImpl.class);
    @Autowired
    private GoodsService goodsService;

    @Override
    @RequestMapping(value = "/goodsDetail.do", method = RequestMethod.GET)
    public ModelAndView goodsDetail(@RequestParam("goodsId") String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        log.info("viewName : " + viewName);
        log.info("goods_id : " + goods_id);
        HttpSession session = request.getSession();
        Map goodsMap = goodsService.goodsDetail(goods_id);
        log.info("goodsMap : " + goodsMap);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsMap", goodsMap);
        GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");
        log.info("goodsVO : " + goodsVO);
        session.setAttribute("goodsInfo",goodsVO);
        addGoodsInQuick(goods_id, goodsVO, session);
        return mav;
    }

    private void addGoodsInQuick(String goodsId, GoodsVO goodsVO, HttpSession session) {
        boolean alreadyExisted = false;
        List<GoodsVO> quickGoodsList;
        quickGoodsList = (ArrayList<GoodsVO>) session.getAttribute("quickGoodsList");

        if (quickGoodsList != null) {
            if (quickGoodsList.size() < 4) {
                for (int i = 0; i < quickGoodsList.size(); i++) {
                    GoodsVO goodsBean = quickGoodsList.get(i);
                    if (goodsId.equals(goodsBean.getGoodsId())) {
                        alreadyExisted = true;
                        break;
                    }
                }
                if (alreadyExisted == false) {
                    quickGoodsList.add(goodsVO);
                }
            }
        } else {
            quickGoodsList = new ArrayList<GoodsVO>();
            quickGoodsList.add(goodsVO);
        }
        session.setAttribute("quickGoodsList", quickGoodsList);
        session.setAttribute("quickGoodsListNum", quickGoodsList.size());
    }

    @Override
    @RequestMapping(value = "/keyWordSearch.do", method = RequestMethod.GET, produces = "application/text;charset=utf-8")
    public @ResponseBody String keyWordSearch(@RequestParam("keyWord") String key_Word, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("여기는 keyWordSearch");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        if (key_Word == null || key_Word.equals("")) {
            return null;
        }
        key_Word = key_Word.toUpperCase();
        log.info("key_Word : " + key_Word);
        List keyWordList = goodsService.keyWordSearch(key_Word);
        log.info("keyWordList : " + keyWordList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyWord", keyWordList);
        String jsonInfo = jsonObject.toString();
        log.info("jsonInfo : " + jsonInfo);
        return jsonInfo;
    }

    @RequestMapping(value = "/searchGoods.do", method = RequestMethod.GET)
    public ModelAndView searchGoods(@RequestParam("searchWord") String search_Word, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("여기는 searchGoods");
        String viewName = (String) request.getAttribute("viewName");
        List goodsList = goodsService.searchGoods(search_Word);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsList", goodsList);
        return mav;
    }


}
