package com.aeo.artwork.serviceImpl;

import com.aeo.artwork.bean.Article;
import com.aeo.artwork.bean.Commodity;
import com.aeo.artwork.bean.Result;
import com.aeo.artwork.bean.TempResult;
import com.aeo.artwork.dao.BackendDao;
import com.aeo.artwork.service.BackendService;
import com.aeo.artwork.tools.CurrentTime;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Transactional(rollbackFor = RuntimeException.class)
@Service
public class BackendServiceImpl implements BackendService {
    @Autowired
    BackendDao backendDao;

    @Override
    public Result login(String username, String password, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        try {
            Integer l = backendDao.login(username, password);
            if (l == null || l == 0) {
                result.getMeta().setMsg("登录失败");
                result.getMeta().setStatus(500);
                return result;
            }
        } catch (Exception e) {
            result.getMeta().setMsg(e.getMessage());
            result.getMeta().setStatus(500);
            return result;
        }
        result.getMeta().setMsg("登录成功");
        result.getMeta().setStatus(200);
        return result;
    }

    @Override
    public Result publish(Map<String, Object> requestMap) {
        Result result = new Result();
//        TempResult result = new TempResult();
        Article article = new Article();
        try {
            if (requestMap.get("title") != null)
                article.setTitle(requestMap.get("title").toString());
            if (requestMap.get("summary") != null)
                article.setSummary(requestMap.get("summary").toString());
            if (requestMap.get("cover") != null)
                article.setCover(JSON.toJSONString(requestMap.get("cover")));
            if (requestMap.get("author") != null)
                article.setAuthor(requestMap.get("author").toString());
            if(requestMap.get("type") != null){
                article.setType((Integer)requestMap.get("type"));
            }else{
                result.getMeta().setStatus(500);
                result.getMeta().setMsg("文章类型不能为空");
                return result;
            }

//            System.out.println(requestMap.get("content"));
            if (requestMap.get("content") != null) {
                String contentStr = JSON.toJSONString(requestMap.get("content"));
//            System.out.println(contentStr);
                article.setContent(contentStr);
            }
            article.setTime(CurrentTime.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            backendDao.publish(article);
//            result.setJsonObject(JSON.parseObject(contentStr));
        } catch (Exception e) {
            e.printStackTrace();
            result.getMeta().setStatus(500);
            result.getMeta().setMsg(e.getMessage());
            return result;
        }
        result.getMeta().setStatus(200);
        result.getMeta().setMsg("发表成功");
        return result;
    }

    @Override
    public Result deleteArticle(Integer id) {
        Result result = new Result();
        try{
            Integer delNum = backendDao.deleteArticle(id);
            if(delNum == null || delNum.equals(0)){
                result.getMeta().setStatus(500);
                result.getMeta().setMsg("删除文章失败");
                return result;
            }
        }catch(Exception e){
            e.printStackTrace();
            result.getMeta().setMsg(e.getMessage());
            result.getMeta().setStatus(500);
            return result;
        }
        result.getMeta().setMsg("删除成功");
        result.getMeta().setStatus(200);
        return result;
    }

    @Override
    public Result editArticle(Map<String, Object> requestMap) {
        Result result = new Result();
        Article article = new Article();
        try {
            if (requestMap.get("title") != null){
                article.setTitle(requestMap.get("title").toString());
            }
            if (requestMap.get("id") != null){
                article.setId((Integer) requestMap.get("id"));
            }else{
                result.getMeta().setStatus(500);
                result.getMeta().setMsg("未指定文章id");
                return result;
            }
            if (requestMap.get("summary") != null)
                article.setSummary(requestMap.get("summary").toString());
            if (requestMap.get("cover") != null)
                article.setCover(JSON.toJSONString(requestMap.get("cover")));
            if (requestMap.get("author") != null)
                article.setAuthor(requestMap.get("author").toString());
            if (requestMap.get("content") != null) {
                String contentStr = JSON.toJSONString(requestMap.get("content"));
                article.setContent(contentStr);
            }
            article.setTime(CurrentTime.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            backendDao.editArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
            result.getMeta().setStatus(500);
            result.getMeta().setMsg(e.getMessage());
            return result;
        }
        result.getMeta().setStatus(200);
        result.getMeta().setMsg("修改成功");
        return result;
    }

    @Override
    public Result publishGoods(Map<String, Object> requestMap) {
        Result result = new Result();
        Commodity commodity = new Commodity();
        try {
            if (requestMap.get("name") != null)
                commodity.setName(requestMap.get("name").toString());
            if (requestMap.get("cover") != null)
                commodity.setCover(JSON.toJSONString(requestMap.get("cover")));
            if (requestMap.get("detail") != null) {
                String detailStr = JSON.toJSONString(requestMap.get("detail"));
//            System.out.println(contentStr);
                commodity.setDetail(detailStr);
            }
            commodity.setTime(CurrentTime.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            backendDao.publishGoods(commodity);
//            result.setJsonObject(JSON.parseObject(contentStr));
        } catch (Exception e) {
            e.printStackTrace();
            result.getMeta().setStatus(500);
            result.getMeta().setMsg(e.getMessage());
            return result;
        }
        result.getMeta().setStatus(200);
        result.getMeta().setMsg("发表商品成功");
        return result;
    }

    @Override
    public Result deleteGoods(Integer id) {
        Result result = new Result();
        try{
            Integer delNum = backendDao.deleteGoods(id);
            if(delNum == null || delNum.equals(0)){
                result.getMeta().setStatus(500);
                result.getMeta().setMsg("删除商品失败");
                return result;
            }
        }catch(Exception e){
            e.printStackTrace();
            result.getMeta().setMsg(e.getMessage());
            result.getMeta().setStatus(500);
            return result;
        }
        result.getMeta().setMsg("删除商品成功");
        result.getMeta().setStatus(200);
        return result;
    }

    @Override
    public Result editGoods(Map<String, Object> requestMap) {
        Result result = new Result();
        Commodity commodity = new Commodity();
        try {
            if (requestMap.get("name") != null){
                commodity.setName(requestMap.get("name").toString());
            }
            if (requestMap.get("id") != null){
                commodity.setId((Integer) requestMap.get("id"));
            }else{
                result.getMeta().setStatus(500);
                result.getMeta().setMsg("未指定商品id");
                return result;
            }
            if (requestMap.get("cover") != null)
                commodity.setCover(JSON.toJSONString(requestMap.get("cover")));
            if (requestMap.get("detail") != null) {
                String detailStr = JSON.toJSONString(requestMap.get("detail"));
                commodity.setDetail(detailStr);
            }
            commodity.setTime(CurrentTime.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            backendDao.editGoods(commodity);
        } catch (Exception e) {
            e.printStackTrace();
            result.getMeta().setStatus(500);
            result.getMeta().setMsg(e.getMessage());
            return result;
        }
        result.getMeta().setStatus(200);
        result.getMeta().setMsg("修改商品成功");
        return result;
    }
}
