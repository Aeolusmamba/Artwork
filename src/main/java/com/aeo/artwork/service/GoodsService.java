package com.aeo.artwork.service;

import com.aeo.artwork.bean.GoodsDetailResult;
import com.aeo.artwork.bean.GoodsListResult;

public interface GoodsService {
    GoodsListResult returnGoodsList(Integer queryId, String queryName, Integer pagenum, Integer pagesize);

    GoodsDetailResult returnGoodsDetail(Integer id);
}
