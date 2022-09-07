package com.aeo.artwork.serviceImpl;

import com.aeo.artwork.bean.*;
import com.aeo.artwork.dao.ArtworkDao;
import com.aeo.artwork.service.ArtworkService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional(rollbackFor = RuntimeException.class)
@Service
public class ArtworkServiceImpl implements ArtworkService {
    @Autowired
    ArtworkDao artworkDao;

    @Override
    public ArticleListResult returnArticleList(Integer queryId, String queryTitle, Integer pagenum, Integer pagesize) {
        ArticleListResult result = new ArticleListResult();
        ArrayList<ArticleInfo> tempArticleList;
        ArrayList<ArticleInfo2> articleList = new ArrayList<>();
        Integer totalNum = 0;
        try{
            if(pagenum == null){  //默认返回第一页内容
                pagenum = 1;
            }
            if(pagesize == null){
                pagesize = 10;
            }
            if(queryId != null){  //按id查询搜索
                tempArticleList = artworkDao.getArticleListByQueryID(queryId, pagesize, (pagenum - 1) * pagesize);
                totalNum = tempArticleList.size();
            }
            else if(queryTitle != null && !queryTitle.equals("")){  //按title查询搜索
                tempArticleList = artworkDao.getArticleListByQueryTitle(queryTitle, pagesize, (pagenum - 1) * pagesize);
                totalNum = artworkDao.getArticleNumByQueryTitle(queryTitle);
            }
            else{
                tempArticleList = artworkDao.getArticleList(pagesize, (pagenum - 1) * pagesize);
                totalNum = artworkDao.getArticleNum();
            }
            if(tempArticleList.isEmpty()){
                result.setMsg("没有更多数据了");
                result.setStatus(500);
                return result;
            }
            for (ArticleInfo a: tempArticleList){
                ArticleInfo2 articleInfo2 = new ArticleInfo2();
                articleInfo2.setId(a.getId());
                articleInfo2.setAuthor(a.getAuthor());
                if(a.getCover() != null && !a.getCover().equals("")){
                    Object object = JSON.parse(a.getCover());
                    if(object instanceof JSONArray){
                        articleInfo2.setCover((JSONArray) object);
                    }
                }
                articleInfo2.setSummary(a.getSummary());
                articleInfo2.setTitle(a.getTitle());
                articleInfo2.setTime(a.getTime());
                articleList.add(articleInfo2);
            }
            result.setArticleList(articleList);
            result.setTotal(articleList.size());
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
            return result;
        }
        result.setMsg("查询成功");
        result.setStatus(200);
        return result;
    }

    @Override
    public ArticleDetailResult returnArticleDetail(Integer id) {
        ArticleDetailResult result = new ArticleDetailResult();
        ArticleDetail articleDetail = new ArticleDetail();
        try{
            Article article = artworkDao.getArticleDetail(id);
            if(article == null){
                result.setStatus(500);
                result.setMsg("该文章不存在");
                return result;
            }
            articleDetail.setId(article.getId());
            articleDetail.setTitle(article.getTitle());
            articleDetail.setSummary(article.getSummary());
            articleDetail.setAuthor(article.getAuthor());
            Object object = JSON.parse(article.getCover());
            if(object instanceof JSONArray){
                articleDetail.setCover((JSONArray) object);
            }
            Object object2 = JSON.parse(article.getContent());
            if(object2 instanceof JSONObject){
                articleDetail.setContent((JSONObject) object2);
            }
            articleDetail.setTime(article.getTime());
            result.setArticle(articleDetail);
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
