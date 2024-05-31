package com.bookMall.goods.service;

import com.bookMall.goods.vo.GoodsVO;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    public Map<String, List<GoodsVO>> listGoods() throws Exception;

    Map goodsDetail(String goodsId) throws Exception;

    List keyWordSearch(String keyWord) throws Exception;

    List searchGoods(String searchWord) throws Exception;
}
