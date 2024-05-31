package com.bookMall.mypage.service;

import com.bookMall.member.vo.MemberVO;
import com.bookMall.order.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface MyPageService {
    public List<OrderVO> listMyOrderGoods(String memberId) throws Exception;

    public void cancelOrder(String orderId) throws Exception;

    public MemberVO modifyMyInfo(Map<String, String> memberMap) throws Exception;
}