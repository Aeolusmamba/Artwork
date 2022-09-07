package com.aeo.artwork.bean;

import java.util.ArrayList;

public class ArticleListResult {
    //    private ArticleListData data = new ArticleListData();
    private Integer status;
    private String msg;
    private ArrayList<ArticleInfo2> articleList = new ArrayList<>();
    private Integer total;
    private Integer pageNum;

    public ArrayList<ArticleInfo2> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<ArticleInfo2> articleList) {
        this.articleList = articleList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
