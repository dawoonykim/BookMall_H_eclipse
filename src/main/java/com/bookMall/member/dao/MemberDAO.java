package com.bookMall.member.dao;

import com.bookMall.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {
//    MemberVO login(Map<String, String> loginMap) throws DataAccessException;

    public MemberVO login(Map<String, String> loginMap) throws DataAccessException;

    String selectOverlappedID(String id) throws DataAccessException;

    void insertNewMember(MemberVO memberVo) throws DataAccessException;
}
