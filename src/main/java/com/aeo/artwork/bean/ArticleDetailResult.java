package com.aeo.artwork.bean;

public class ArticleDetailResult {
    private ArticleDetail article;
    private Integer status;
    private String msg;

    public ArticleDetail getArticle() {
        return article;
    }

    public void setArticle(ArticleDetail article) {
        this.article = article;
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
}
