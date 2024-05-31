package com.bookMall.order.controller;

import com.bookMall.order.vo.OrderVO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface OrderController {
    public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO order_VO, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView orderAllCartGoods(@RequestParam("h_order_each_goods_qty") String hOrderEachGoodsQty, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView payToOrderGoods(@RequestParam Map<String, String> orderMap, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
