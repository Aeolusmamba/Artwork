package com.aeo.artwork.controller;


import com.aeo.artwork.bean.ArticleDetailResult;
import com.aeo.artwork.bean.ArticleListResult;
import com.aeo.artwork.bean.Result;
import com.aeo.artwork.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtworkController {
    @Autowired
    ArtworkService artworkService;

    @RequestMapping(value = "/artwork/articleList", method = {RequestMethod.GET})
    public ArticleListResult returnArticleList(@RequestParam(value = "queryId", required = false) Integer queryId,
                                            @RequestParam(value = "queryTitle", required = false) String queryTitle,
                                            @RequestParam(value = "pagenum", required = false) Integer pagenum,
                                            @RequestParam(value = "pagesize", required = false) Integer pagesize,
                                               @RequestParam(value = "type", required = false) Integer type) {
        return artworkService.returnArticleList(queryId, queryTitle, pagenum, pagesize, type);
    }

    @RequestMapping(value = "/artwork/articleDetail", method = {RequestMethod.GET})
    public ArticleDetailResult returnArticleDetail(@RequestParam("id") Integer id) {
        return artworkService.returnArticleDetail(id);
    }

}
