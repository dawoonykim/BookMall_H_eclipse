package com.bookMall.mypage.dao;

import com.bookMall.member.vo.MemberVO;
import com.bookMall.order.vo.OrderVO;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("myPageDAOImpl")
public class MyPageDAOImpl implements MyPageDAO {

    private static final Logger log = LoggerFactory.getLogger(MyPageDAOImpl.class);
    @Autowired
    private SqlSession session;

    @Override
    public List<OrderVO> selectMyOrderGoodsList(String memberId) throws DataAccessException {
        return session.selectList("mapper.mypage.selectMyOrderGoodsList", memberId);
    }

    @Override
    public void updateMyOrderCancel(String orderId) throws DataAccessException {
        session.update("mapper.mypage.updateMyOrderCancel", orderId);
    }

    @Override
    public void updateMyInfo(Map memberMap) throws DataAccessException {
        log.info("MyPageDAOImpl updateMyInfo memberMap : " + memberMap.toString());
        session.update("mapper.mypage.updateMyInfo", memberMap);
    }

    @Override
    public MemberVO selectMyDetatilInfo(String memberId) throws DataAccessException {
        log.info("MyPageDAOImpl selectMyDetatilInfo memberId : " + memberId);
        MemberVO memberVO = session.selectOne("mapper.mypage.selectMyDetatilInfo", memberId);
        log.info("MyPageDAOImpl selectMyDetatilInfo memberVO : " + memberVO);
        return memberVO;
    }


}