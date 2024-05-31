package com.bookMall.goods.dao;

import com.bookMall.goods.vo.GoodsVO;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public interface GoodsDAO {
    public List<GoodsVO> selectGoodsList(String goodsStatus) throws DataAccessException;

    GoodsVO selectGoodsDetail(String goodsId) throws DataAccessException;

    List selectGoodsDetailImage(String goodsId) throws DataAccessException;

    List<String> selectKeywordSearch(String keyWord) throws DataAccessException;


    ArrayList selectGoodsBySearchWord(String searchWord) throws DataAccessException;
}
