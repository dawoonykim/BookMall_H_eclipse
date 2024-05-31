package com.bookMall.admin.goods.service;

import com.bookMall.admin.goods.dao.AdminGoodsDAO;
import com.bookMall.goods.vo.GoodsVO;
import com.bookMall.goods.vo.ImageFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminGoodsServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminGoodsServiceImpl implements AdminGoodsService {

    private final static Logger log = LoggerFactory.getLogger(AdminGoodsServiceImpl.class);
    @Autowired
    private AdminGoodsDAO adminGoodsDAO;

    @Override
    public int addNewGoods(Map newGoodsMap) throws Exception {
        log.info("AdminGoodsServiceImpl addNewGoods 실행");
        int goodsId = adminGoodsDAO.insertNewGoods(newGoodsMap);
        log.info("AdminGoodsServiceImpl addNewGoods goodsId : " + goodsId);
        ArrayList<ImageFileVO> imageFileList = (ArrayList<ImageFileVO>) newGoodsMap.get("imageFileList");
        log.info("AdminGoodsServiceImpl addNewGoods imageFileList : " + imageFileList);
        log.info("AdminGoodsServiceImpl addNewGoods for 전");
        for (ImageFileVO imageFileVO : imageFileList) {
            imageFileVO.setGoodsId(goodsId);
        }
        log.info("AdminGoodsServiceImpl addNewGoods for 후/ insertGoodsImageFile 전");
        adminGoodsDAO.insertGoodsImageFile(imageFileList);
        log.info("AdminGoodsServiceImpl addNewGoods insertGoodsImageFile 후");
        return goodsId;
    }

    @Override
    public List<GoodsVO> listNewGoods(Map condMap) throws Exception {
        return adminGoodsDAO.selectNewGoodsList(condMap);
    }

    @Override
    public Map goodsDetail(int goodsId) throws Exception {
        Map goodsMap = new HashMap();
        GoodsVO goodsVO = adminGoodsDAO.selectGoodsDetail(goodsId);
        List imageFileList = adminGoodsDAO.selectGoodsImageFileList(goodsId);
        goodsMap.put("goods", goodsVO);
        goodsMap.put("imageFileList", imageFileList);
        return goodsMap;
    }

    @Override
    public void modifyGoodsInfo(Map<String, String> goodsMap) throws Exception {
        log.info("AdminGoodsServiceImpl modifyGoodsInfo goodsMap : "+goodsMap.toString());
        adminGoodsDAO.updateGoodsInfo(goodsMap);
    }

    @Override
    public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception {
        log.info("AdminGoodsServiceImpl modifyGoodsInfo imageFileList : "+imageFileList.toString());
        adminGoodsDAO.updateGoodsImage(imageFileList);

    }

    @Override
    public void addNewGoodsImage(List imageFileList) throws Exception {
        adminGoodsDAO.insertGoodsImageFile(imageFileList);
    }
    @Override
    public void removeGoodsImage(int imageId) throws Exception {
        log.info("AdminGoodsServiceImpl removeGoodsImage 실행");
        log.info("imageId : "+imageId);
        adminGoodsDAO.deleteGoodsImage(imageId);
    }

}