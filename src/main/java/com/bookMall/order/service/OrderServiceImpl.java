package com.bookMall.order.service;

import com.bookMall.order.dao.OrderDAO;
import com.bookMall.order.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "orderServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDAO orderDAO;

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Override
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception {
        log.info("OrderService addNewOrder 작동 전");
        orderDAO.insertNewOrder(myOrderList);
        log.info("OrderService addNewOrder insertNewOrder 동작");
        orderDAO.removeGoodsFromCart(myOrderList);
        log.info("OrderService addNewOrder removeGoodsFromCart 동작");
    }
}
