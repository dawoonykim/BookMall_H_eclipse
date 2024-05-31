package com.bookMall.order.service;

import com.bookMall.order.vo.OrderVO;

import java.util.List;

public interface OrderService {
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception;
}
