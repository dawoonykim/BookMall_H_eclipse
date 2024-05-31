package com.bookMall.order.dao;

import com.bookMall.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OrderDAO {
    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;

    public void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException;
}
