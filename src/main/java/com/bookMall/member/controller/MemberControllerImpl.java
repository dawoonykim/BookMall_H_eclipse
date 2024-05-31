package com.bookMall.member.controller;

import com.bookMall.common.base.BaseController;
import com.bookMall.member.service.MemberService;
import com.bookMall.member.vo.MemberVO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller(value = "memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberControllerImpl.class);
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberVO memberVO;

    @Override
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("MemberControllerImpl loginMap : " + loginMap);
        ModelAndView mav = new ModelAndView();
        log.info("MemberControllerImpl mav : " + mav);
        memberVO = memberService.login(loginMap);

        if (memberVO != null && memberVO.getMemberId() != null) {
            // 로그인 성공
            HttpSession session = request.getSession();
            log.info("MemberControllerImpl session : " + session);
            session.setAttribute("isLogOn", true);
            session.setAttribute("memberInfo", memberVO);

            String action = (String) session.getAttribute("action");
            log.info("MemberControllerImpl action : " + action);
            if (action != null && action.equals("/order/orderEachGoods.do")) {
                mav.setViewName("forward:" + action);
            } else {
                mav.setViewName("redirect:/main/main.do");
            }
        } else {
            // 로그인 실패
            String message = "아이디나 비밀번호가 틀립니다. 다시 로그인해주세요";
            mav.addObject("message", message);
            mav.setViewName("/member/loginForm");
        }

        return mav;
    }



    @Override
    @RequestMapping(value = "/overlapped.do", method = RequestMethod.POST)
    public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("MemberControllerImpl overlapped id : " + id);
        ResponseEntity resEntity;
        String result = memberService.overlapped(id);
        resEntity = new ResponseEntity(result, HttpStatus.OK);
        return resEntity;
    }

    @Override
    @RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO member_VO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String message = null;
        log.info("addMember message : " + message);
        ResponseEntity resEntity = null;
        log.info("addMember resEntity : " + resEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/html;charset=utf-8");
        log.info("addMember httpHeaders : " + httpHeaders);
        try {
            memberService.addMember(member_VO);
            message = "<script>";
            message += "alert('회원 가입을 마쳤습니다. 로그인 창으로 이동합니다.');";
            message += "location.href='" + request.getContextPath() + "/member/loginForm.do';";
            message += "</script>";
        } catch (Exception e) {
            message = "<script>";
            message += "alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
            message += "location.href='" + request.getContextPath() + "/member/memberForm.do';";
            message += "</script>";
            e.printStackTrace();
        }

        resEntity = new ResponseEntity(message, httpHeaders, HttpStatus.OK);
        return resEntity;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(@NotNull HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav=new ModelAndView();
        HttpSession session=request.getSession();
        session.invalidate();
        mav.setViewName("redirect:/main/main.do");
        return mav;
    }


}
