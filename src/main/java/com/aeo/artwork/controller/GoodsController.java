package com.aeo.artwork.controller;

import com.aeo.artwork.bean.GoodsDetailResult;
import com.aeo.artwork.bean.GoodsListResult;
import com.aeo.artwork.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/artwork/goodsList", method = {RequestMethod.GET})
    public GoodsListResult returnGoodsList(@RequestParam(value = "queryId", required = false) Integer queryId,
                                           @RequestParam(value = "queryName", required = false) String queryName,
                                           @RequestParam(value = "pagenum", required = false) Integer pagenum,
                                           @RequestParam(value = "pagesize", required = false) Integer pagesize) {
        return goodsService.returnGoodsList(queryId, queryName, pagenum, pagesize);
    }

    @RequestMapping(value = "/artwork/goodsDetail", method = {RequestMethod.GET})
    public GoodsDetailResult returnGoodsDetail(@RequestParam("id") Integer id) {
        return goodsService.returnGoodsDetail(id);
    }
}
