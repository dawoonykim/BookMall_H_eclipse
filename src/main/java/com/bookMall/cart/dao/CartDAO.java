package com.bookMall.cart.dao;

import com.bookMall.cart.vo.CartVO;
import com.bookMall.goods.vo.GoodsVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CartDAO {
    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException;

    public void insertGoodsInCart(CartVO cartVO) throws DataAccessException;

    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;

    public List<GoodsVO> selectGoodsList(List<CartVO> myCartList) throws DataAccessException;

    public void deleteCartGoods(int cartId) throws DataAccessException;

    public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException;
}
