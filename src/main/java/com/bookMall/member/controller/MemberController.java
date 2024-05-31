package com.bookMall.member.controller;

import com.bookMall.member.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MemberController {
    public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ResponseEntity overlapped(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response)throws Exception;

    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO member_VO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
