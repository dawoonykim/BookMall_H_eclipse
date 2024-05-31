package com.bookMall.goods.service;

import com.bookMall.goods.dao.GoodsDAO;
import com.bookMall.goods.vo.GoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsService")
@Transactional(propagation = Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService {
    private static final Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);
    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public Map<String, List<GoodsVO>> listGoods() throws Exception {
        Map<String, List<GoodsVO>> goodsMap = new HashMap<String, List<GoodsVO>>();
        List<GoodsVO> goodsList = goodsDAO.selectGoodsList("bestseller");
        goodsMap.put("bestseller", goodsList);

        goodsList = goodsDAO.selectGoodsList("newbook");
        goodsMap.put("newbook", goodsList);

        goodsList = goodsDAO.selectGoodsList("steadyseller");
        goodsMap.put("steadyseller", goodsList);
        return goodsMap;
    }

    @Override
    public Map goodsDetail(String goodsId) throws Exception {
        Map goodsMap = new HashMap();
        GoodsVO goodsVO = goodsDAO.selectGoodsDetail(goodsId);
        goodsMap.put("goodsVO", goodsVO);
        List imageList = goodsDAO.selectGoodsDetailImage(goodsId);
        goodsMap.put("imageList", imageList);
        return goodsMap;
    }

    public List<String> keyWordSearch(String keyWord) throws Exception {
        log.info("GoodsServiceImpl¿« keyWordSerach¿« keyWord : " + keyWord);
        List<String> goodsList = goodsDAO.selectKeywordSearch(keyWord);
        log.info("goodsList : " + goodsList);
        return goodsList;
    }

    public List searchGoods(String searchWord) throws Exception {
        List goodsList = goodsDAO.selectGoodsBySearchWord(searchWord);
        return goodsList;
    }

}
