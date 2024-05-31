package com.bookMall.cart.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartController {
    public @ResponseBody Object addGoodsInCart(@RequestParam("goodsId") String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView removeCartGoods(@RequestParam("cartId") int cart_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public @ResponseBody String modifyCartQty(@RequestParam("goodsId") int goods_id, @RequestParam("cartGoodsQty") int cart_goods_qty, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
