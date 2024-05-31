package com.bookMall.mypage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyPageController {

    public ModelAndView myPageMain(@RequestParam(required = false, value = "message") String message, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView cancelMyOrder(@RequestParam("orderId") String order_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ResponseEntity modifyMyInfo(@RequestParam("attribute") String attribute, @RequestParam("value") String value, HttpServletRequest request, HttpServletResponse response) throws Exception;

}