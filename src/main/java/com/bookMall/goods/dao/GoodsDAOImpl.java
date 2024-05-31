package com.bookMall.goods.dao;

import com.bookMall.goods.vo.GoodsVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO {
    private static final Logger log = LoggerFactory.getLogger(GoodsDAOImpl.class);
    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList selectGoodsList(String goodsStatus) throws DataAccessException {
        return (ArrayList) sqlSession.selectList("mapper.goods.selectGoodsList", goodsStatus);
    }

    @Override
    public GoodsVO selectGoodsDetail(String goodsId) throws DataAccessException {
        return (GoodsVO) sqlSession.selectOne("mapper.goods.selectGoodsDetail", goodsId);
    }

    @Override
    public List selectGoodsDetailImage(String goodsId) throws DataAccessException {
        return sqlSession.selectList("mapper.goods.selectGoodsDetailImage", goodsId);
    }

    @Override
    public List<String> selectKeywordSearch(String keyWord) throws DataAccessException {
        log.info("selectKeywordSearch keyWord : " + keyWord);
        return sqlSession.selectList("mapper.goods.selectKeywordSearch", keyWord);
    }

    @Override
    public ArrayList selectGoodsBySearchWord(String searchWord) throws DataAccessException {
        return (ArrayList) sqlSession.selectList("mapper.goods.selectGoodsBySearchWord", searchWord);
    }
}
