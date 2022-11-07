package com.aeo.artwork.dao;

import com.aeo.artwork.bean.Commodity;
import com.aeo.artwork.bean.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface GoodsDao {

    @Select("Select id, name, cover, link, price, time from commodity where id=#{queryId}" +
            " limit #{limit} offset #{offset}")
    ArrayList<GoodsInfo> getGoodsListByQueryID(@Param("queryId") Integer queryId, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Select("Select id, name, cover, link, price, time from commodity where name like concat('%', #{queryName}, '%') " +
            "limit #{limit} offset #{offset}")
    ArrayList<GoodsInfo> getGoodsListByQueryName(@Param("queryName") String queryName, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Select("Select count(*) from commodity where name like concat('%', #{queryName}, '%')")
    Integer getGoodsNumByQueryName(@Param("queryName") String queryName);

    @Select("Select id, name, cover, link, price, time from commodity limit #{limit} offset #{offset}")
    ArrayList<GoodsInfo> getGoodsList(@Param("limit") Integer limit, @Param("offset") Integer offset);


    @Select("Select count(*) from commodity")
    Integer getGoodsNum();

    @Select("Select * from commodity where id = #{id}")
    Commodity getGoodsDetail(Integer id);
}
