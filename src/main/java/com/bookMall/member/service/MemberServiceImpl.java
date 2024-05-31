package com.bookMall.member.service;

import com.bookMall.member.dao.MemberDAO;
import com.bookMall.member.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service(value = "memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public MemberVO login(Map<String, String> loginMap) throws Exception {
        log.info("MemberServiceImpl login(Map<String, String>) loginMap : " + loginMap);
        log.info("memberDAO.login(loginMap) : "+(memberDAO.login(loginMap).getMemberId())+" "+(memberDAO.login(loginMap).getMemberPw()));
        return memberDAO.login(loginMap);
    }

    @Override
    public String overlapped(String id) throws Exception {
        log.info("MemberServiceImpl overlapped id : " + id);
        return memberDAO.selectOverlappedID(id);
    }

    @Override
    public void addMember(MemberVO memberVo) throws Exception {
        log.info("MemberServiceImpl addMember memberVo : " + memberVo);
        memberDAO.insertNewMember(memberVo);
    }
}
