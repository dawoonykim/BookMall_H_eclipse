package com.bookMall.admin.goods.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminGoodsController {

    public ResponseEntity addNewGoods(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView modifyGoodsForm(@RequestParam("goodsId") int goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ResponseEntity modifyGoodsInfo(@RequestParam("goodsId") String goodsId, @RequestParam("attribute") String attribute, @RequestParam("value") String value, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void modifyGoodsImageInfo(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

    public void addNewGoodsImage(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

    public void removeGoodsImage(@RequestParam("goodsId") int goodsId, @RequestParam("imageId") int imageId, @RequestParam("imageFileName") String imageFileName, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
