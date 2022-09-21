package com.aeo.artwork.serviceImpl;

import com.aeo.artwork.bean.*;
import com.aeo.artwork.dao.GoodsDao;
import com.aeo.artwork.service.GoodsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional(rollbackFor = RuntimeException.class)
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;

    @Override
    public GoodsListResult returnGoodsList(Integer queryId, String queryName, Integer pagenum, Integer pagesize) {
        GoodsListResult result = new GoodsListResult();
        ArrayList<GoodsInfo> tempGoodsList;
        ArrayList<GoodsInfo2> goodsList = new ArrayList<>();
        Integer totalNum = 0;
        try{
            if(pagenum == null){  //默认返回第一页内容
                pagenum = 1;
            }
            if(pagesize == null){
                pagesize = 10;
            }
            if(queryId != null){  //按id查询搜索
                tempGoodsList = goodsDao.getGoodsListByQueryID(queryId, pagesize, (pagenum - 1) * pagesize);
                totalNum = tempGoodsList.size();
            }
            else if(queryName != null && !queryName.equals("")){  //按title查询搜索
                tempGoodsList = goodsDao.getGoodsListByQueryName(queryName, pagesize, (pagenum - 1) * pagesize);
                totalNum = goodsDao.getGoodsNumByQueryName(queryName);
            }
            else{
                tempGoodsList = goodsDao.getGoodsList(pagesize, (pagenum - 1) * pagesize);
                totalNum = goodsDao.getGoodsNum();
            }
            if(tempGoodsList.isEmpty()){
                result.setMsg("没有更多数据了");
                result.setStatus(500);
                return result;
            }
            for (GoodsInfo g: tempGoodsList){
                GoodsInfo2 goodsInfo2 = new GoodsInfo2();
                goodsInfo2.setId(g.getId());
                if(g.getCover() != null && !g.getCover().equals("")){
                    Object object = JSON.parse(g.getCover());
                    if(object instanceof JSONArray){
                        goodsInfo2.setCover((JSONArray) object);
                    }
                }
                goodsInfo2.setName(g.getName());
                goodsInfo2.setTime(g.getTime());
                goodsList.add(goodsInfo2);
            }
            result.setGoodsList(goodsList);
            result.setTotal(goodsList.size());
            //总页数
            if(totalNum <= pagesize){
                result.setPageNum(1);
            }else{
                result.setPageNum((int) Math.ceil(totalNum / (1.0 * pagesize)));
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage());
            result.setStatus(500);
        }
        result.setMsg("查询商品列表成功");
        result.setStatus(200);
        return result;
    }

    @Override
    public GoodsDetailResult returnGoodsDetail(Integer id) {
        GoodsDetailResult result = new GoodsDetailResult();
        GoodsDetail goodsDetail = new GoodsDetail();
        try{
            Commodity commodity = goodsDao.getGoodsDetail(id);
            if(commodity == null){
                result.setStatus(500);
                result.setMsg("该商品不存在");
                return result;
            }
            goodsDetail.setId(commodity.getId());
            goodsDetail.setName(commodity.getName());
            Object object = JSON.parse(commodity.getCover());
            if(object instanceof JSONArray){
                goodsDetail.setCover((JSONArray) object);
            }
            Object object2 = JSON.parse(commodity.getDetail());
            if(object2 instanceof JSONObject){
                goodsDetail.setDetail((JSONObject) object2);
            }
            goodsDetail.setTime(commodity.getTime());
            result.setGoods(goodsDetail);
        }catch(Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage());
            result.setStatus(500);
            return result;
        }
        result.setStatus(200);
        result.setMsg("查看成功");
        return result;
    }
}
