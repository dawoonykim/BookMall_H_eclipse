package com.bookMall.goods.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GoodsController {

    public ModelAndView goodsDetail(String goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public @ResponseBody String keyWordSearch(@RequestParam("keyWord") String key_Word, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
