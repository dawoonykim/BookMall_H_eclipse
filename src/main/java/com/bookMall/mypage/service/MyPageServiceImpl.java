package com.bookMall.mypage.service;

import com.bookMall.member.vo.MemberVO;
import com.bookMall.mypage.dao.MyPageDAO;
import com.bookMall.order.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("myPageServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class MyPageServiceImpl implements MyPageService {
    private static final Logger log = LoggerFactory.getLogger(MyPageServiceImpl.class);
    @Autowired
    private MyPageDAO myPageDAO;

    @Override
    public List<OrderVO> listMyOrderGoods(String memberId) throws Exception {
        return myPageDAO.selectMyOrderGoodsList(memberId);
    }

    @Override
    public void cancelOrder(String orderId) throws Exception {
        myPageDAO.updateMyOrderCancel(orderId);
    }

    @Override
    public MemberVO modifyMyInfo(Map<String, String> memberMap) throws Exception {
        log.info("MyPageServiceImpl modifyMyInfo memberMap : " + memberMap.toString());
        String memberId = memberMap.get("memberId");
        log.info("MyPageServiceImpl modifyMyInfo memberId : " + memberId);
        log.info("MyPageServiceImpl modifyMyInfo myPageDAO.updateMyInfo(memberMap) 전");
        myPageDAO.updateMyInfo(memberMap);
        log.info("MyPageServiceImpl modifyMyInfo myPageDAO.updateMyInfo(memberMap) 후");
        return myPageDAO.selectMyDetatilInfo(memberId);
    }
}