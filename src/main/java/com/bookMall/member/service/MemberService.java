package com.bookMall.member.service;

import com.bookMall.member.vo.MemberVO;

import java.util.Map;

public interface MemberService {
    MemberVO login(Map<String, String> loginMap) throws Exception;

    String overlapped(String id) throws Exception;

    void addMember(MemberVO memberVo) throws Exception;
}
