package com.aeo.artwork.service;

import com.aeo.artwork.bean.ArticleDetailResult;
import com.aeo.artwork.bean.ArticleListResult;
import com.aeo.artwork.bean.Result;

public interface ArtworkService {

    ArticleListResult returnArticleList(Integer queryId, String queryTitle, Integer pagenum, Integer pagesize);

    ArticleDetailResult returnArticleDetail(Integer id);
}
