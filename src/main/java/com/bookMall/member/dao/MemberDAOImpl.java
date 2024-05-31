package com.bookMall.member.dao;

import com.bookMall.member.vo.MemberVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Repository(value = "memberDAO")
public class MemberDAOImpl implements MemberDAO {

    private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);

    @Autowired
    private SqlSession session;

    @Override
    public MemberVO login(Map<String, String> loginMap) throws DataAccessException {
        log.info("MemberDAOImpl login(Map<String, String>) loginMap : " + loginMap);
        MemberVO memberVO = (MemberVO) session.selectOne("mapper.member.login", loginMap);
        if (memberVO != null) {
            log.info("MemberDAOImpl memberVO : " + memberVO.getMemberId() + " " + memberVO.getMemberPw());
        } else {
            log.info("MemberDAOImpl memberVO is null");
            memberVO = new MemberVO();
        }
        return memberVO;
    }



    @Override
    public String selectOverlappedID(String id) throws DataAccessException {
        log.info("MemberDAOImpl overlapped id : " + id);
        return session.selectOne("mapper.member.selectOverlappedID", id);
    }

    @Override
    public void insertNewMember(MemberVO memberVo) throws DataAccessException {
        log.info("MemberDAOImpl insertNewMember(MemberVO memberVo) : " + memberVo.getMemberId() + " " + memberVo.getMemberPw());
        session.insert("mapper.member.insertNewMember", memberVo);
    }
}
