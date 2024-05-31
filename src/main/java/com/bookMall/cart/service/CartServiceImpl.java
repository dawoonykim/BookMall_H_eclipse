package com.bookMall.cart.service;

import com.bookMall.cart.dao.CartDAO;
import com.bookMall.cart.vo.CartVO;
import com.bookMall.goods.vo.GoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "cartService")
public class CartServiceImpl implements CartService {

    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    private CartDAO cartDAO;

    @Override
    public boolean findCartGoods(CartVO cartVO) throws Exception {
        log.info("CartServiceImpl findCartGoods cartVO : " + cartVO.getCartId() + " " + cartVO.getGoodsId() + " " + cartVO.getMemberId());
        return cartDAO.selectCountInCart(cartVO);
    }

    @Override
    public void addGoodsInCart(CartVO cartVO) throws Exception {
        log.info("CartServiceImpl addGoodsInCart cartVO : " + cartVO.getCartId() + " " + cartVO.getGoodsId() + " " + cartVO.getMemberId());
        cartDAO.insertGoodsInCart(cartVO);
    }

    @Override
    public Map<String, List> myCartList(CartVO cartVO) throws Exception {
        log.info("CartServiceImpl myCartList cartVO : " + cartVO.getCartId() + " " + cartVO.getGoodsId() + " " + cartVO.getMemberId());
        Map<String, List> cartMap = new HashMap<String, List>();
        log.info("CartServiceImpl myCartList cartMap : " + cartMap);
        List<CartVO> myCartList=cartDAO.selectCartList(cartVO);

        if(myCartList.size()==0){
            return null;
        }
        List<GoodsVO> myGoodsList = cartDAO.selectGoodsList(myCartList);
        cartMap.put("myCartList", myCartList);
        cartMap.put("myGoodsList", myGoodsList);
        return cartMap;
    }

    @Override
    public void removeCartGoods(int cartId) throws Exception {
        cartDAO.deleteCartGoods(cartId);
    }

    @Override
    public boolean modifyCartQty(CartVO cartVO) throws Exception {
        boolean result = true;
        cartDAO.updateCartGoodsQty(cartVO);
        return result;
    }
}
