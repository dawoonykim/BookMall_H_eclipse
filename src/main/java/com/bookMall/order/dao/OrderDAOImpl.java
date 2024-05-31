package com.bookMall.order.dao;

import com.bookMall.order.vo.OrderVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "orderDAOImpl")
public class OrderDAOImpl implements OrderDAO {
    private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);
    @Autowired
    private SqlSession session;

    @Override
    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException {
        int order_id = selectOrderId();
        log.info("OrderDAOImpl insertNewOrder order_id : " + order_id);
        for (int i = 0; i < myOrderList.size(); i++) {
            OrderVO orderVO = myOrderList.get(i);
            orderVO.setOrderId(order_id);
            session.insert("mapper.order.insertNewOrder", orderVO);
        }
//        log.info("OrderDAOImpl insertNewOrder 종료");
    }

    private int selectOrderId() throws DataAccessException {
        return session.selectOne("mapper.order.selectOrderId");
    }

    @Override
    public void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException {
        for(int i=0;i<myOrderList.size();i++){
            OrderVO orderVO = myOrderList.get(i);
            session.delete("mapper.order.deleteGoodsFromCart", orderVO);
        }
    }
}
