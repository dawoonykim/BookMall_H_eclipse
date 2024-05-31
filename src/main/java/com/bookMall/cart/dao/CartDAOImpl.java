package com.bookMall.cart.dao;

import com.bookMall.cart.vo.CartVO;
import com.bookMall.goods.vo.GoodsVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "cartDAO")
public class CartDAOImpl implements CartDAO {

    private static final Logger log = LoggerFactory.getLogger(CartDAOImpl.class);
    @Autowired
    private SqlSession session;

    @Override
    public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
        String result = session.<String>selectOne("mapper.cart.selectCountInCart", cartVO);
        log.info("CartDAOImpl selectCountInCart result : " + result);
        return Boolean.parseBoolean(result);
    }

    @Override
    public void insertGoodsInCart(CartVO cartVO) throws DataAccessException {
        int cart_id = selectMaxCartId();
        log.info("CartDAOImpl insertGoodsInCart cart_id : " + cart_id);
        cartVO.setCartId(cart_id);
        log.info("CartDAOImpl insertGoodsInCart cartVO : " + cartVO.getCartId() + " " + cartVO.getGoodsId() + " " + cartVO.getMemberId());
//        session.insert("mapper.cart.insertGoodsInCart", cartVO);
        log.info("CartDAOImpl insertGoodsInCart(CartVO cartVO) : " + (session.insert("mapper.cart.insertGoodsInCart", cartVO)));
    }

    private int selectMaxCartId() throws DataAccessException {
        int cart_id = session.selectOne("mapper.cart.selectMaxCartId");
        log.info("CartDAOImpl selectMaxCartId cart_id : " + cart_id);
        return cart_id;
    }


    @Override
    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
        log.info("This is CartDAOImpl selectCartList place");
        List<CartVO> cartList = (List) session.selectList("mapper.cart.selectCartList", cartVO);
        log.info("CartDAOImpl selectCartList cartList : " + cartList);
        return cartList;
    }

    @Override
    public List<GoodsVO> selectGoodsList(List<CartVO> myCartList) throws DataAccessException {
        List<GoodsVO> myGoodsList;
        myGoodsList = session.selectList("mapper.cart.selectGoodsList", myCartList);
        log.info("CartDAOImpl selectGoodsList myGoodsList : " + myGoodsList);
        return myGoodsList;
    }

    @Override
    public void deleteCartGoods(int cartId) throws DataAccessException {
        log.info("CartDAOImpl deleteCartGoods cartId : " + cartId + "가 삭제되었습니다.");
        session.delete("mapper.cart.deleteCartGoods", cartId);
    }

    @Override
    public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException {
        log.info("CartDAOImpl updateCartGoodsQty cartVO "+cartVO.getGoodsId()+" "+cartVO.getMemberId()+" "+cartVO.getCartGoodsQty()+"가 수정되었습니다.");
        session.update("mapper.cart.updateCartGoodsQty", cartVO);
    }
}
