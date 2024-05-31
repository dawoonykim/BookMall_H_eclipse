package com.bookMall.mypage.dao;

import com.bookMall.member.vo.MemberVO;
import com.bookMall.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface MyPageDAO {
    public List<OrderVO> selectMyOrderGoodsList(String memberId) throws DataAccessException;

    public void updateMyOrderCancel(String orderId) throws DataAccessException;

    public void updateMyInfo(Map memberMap) throws DataAccessException;

    public MemberVO selectMyDetatilInfo(String memberId) throws DataAccessException;
}