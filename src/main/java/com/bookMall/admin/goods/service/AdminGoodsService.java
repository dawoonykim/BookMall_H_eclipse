package com.bookMall.admin.goods.service;

import com.bookMall.goods.vo.GoodsVO;
import com.bookMall.goods.vo.ImageFileVO;

import java.util.List;
import java.util.Map;

public interface AdminGoodsService {
    public int addNewGoods(Map newGoodsMap) throws Exception;

    public List<GoodsVO> listNewGoods(Map condMap) throws Exception;

    public Map goodsDetail(int goodsId) throws Exception;

    public void modifyGoodsInfo(Map<String, String> goodsMap) throws Exception;

    public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;

    public void addNewGoodsImage(List<ImageFileVO> imageFileList) throws Exception;

    public void removeGoodsImage(int imageId) throws Exception;
}
