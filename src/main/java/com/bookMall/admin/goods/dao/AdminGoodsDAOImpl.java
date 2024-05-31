package com.bookMall.admin.goods.dao;

import com.bookMall.goods.vo.GoodsVO;
import com.bookMall.goods.vo.ImageFileVO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("adminGoodsDAOImpl")
public class AdminGoodsDAOImpl implements AdminGoodsDAO {

    private final static Logger log = LoggerFactory.getLogger(AdminGoodsDAOImpl.class);
    @Autowired
    private SqlSession session;

    @Override
    public int insertNewGoods(Map newGoodsMap) throws DataAccessException {
        log.info("AdminGoodsDAOImpl insertNewGoods 실행");
        session.insert("mapper.admin.goods.insertNewGoods", newGoodsMap);
        log.info("AdminGoodsDAOImpl insertNewGoods insert 후");
        return Integer.parseInt((String) newGoodsMap.get("goodsId"));
    }

    @Override
    public void insertGoodsImageFile(List fileList) throws DataAccessException {
        log.info("AdminGoodsDAOImpl insertGoodsImageFile 실행");
        for (int i = 0; i < fileList.size(); i++) {
            ImageFileVO imageFileVO = (ImageFileVO) fileList.get(i);
            log.info("AdminGoodsDAOImpl insertGoodsImageFile ImageFileVO : " + imageFileVO);
            session.insert("mapper.admin.goods.insertGoodsImageFile", imageFileVO);
        }
    }


    @Override
    public List<GoodsVO> selectNewGoodsList(Map condMap) throws DataAccessException {
        ArrayList<GoodsVO> goodsList = (ArrayList) session.selectList("mapper.admin.goods.selectNewGoodsList", condMap);
        return goodsList;
    }

    @Override
    public GoodsVO selectGoodsDetail(int goodsId) throws DataAccessException {
        GoodsVO goodsBean = new GoodsVO();
        goodsBean = session.selectOne("mapper.admin.goods.selectGoodsDetail", goodsId);
        return goodsBean;
    }

    @Override
    public List selectGoodsImageFileList(int goodsId) throws DataAccessException {
        List imageList = new ArrayList();
        imageList = session.selectList("mapper.admin.goods.selectGoodsImageFileList", goodsId);
        return imageList;
    }

    @Override
    public void updateGoodsInfo(Map<String, String> goodsMap) throws DataAccessException {

        log.info("AdminGoodsDAOImpl updateGoodsInfo goodsMap : " + goodsMap.toString());
        session.update("mapper.admin.goods.updateGoodsInfo", goodsMap);
    }

    @Override
    public void updateGoodsImage(List<ImageFileVO> imageFileList) throws DataAccessException {
        log.info("AdminGoodsDAOImpl updateGoodsImage imageFileList : "+imageFileList.toString());
        for (int i = 0; i < imageFileList.size(); i++) {
            ImageFileVO imageFileVO = imageFileList.get(i);
            log.info("AdminGoodsDAOImpl updateGoodsImage imageFileVO : "+imageFileVO.toString());
            session.update("mapper.admin.goods.updateGoodsImage", imageFileVO);
        }
    }

    @Override
    public void deleteGoodsImage(int imageId) throws DataAccessException {
        log.info("adminGoodsDAOImpl deleteGoodsImage 실행");
        log.info("imageId : "+imageId);
        session.delete("mapper.admin.goods.deleteGoodsImage", imageId);
    }

}