package com.aeo.artwork.bean;

import java.util.ArrayList;

public class ArticleListData {
    private ArrayList<ArticleInfo2> articleList = new ArrayList<>();
    private Integer total;

    public ArrayList<ArticleInfo2> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<ArticleInfo2> articleList) {
        this.articleList = articleList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
