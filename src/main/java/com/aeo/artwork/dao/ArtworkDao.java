package com.aeo.artwork.dao;

import com.aeo.artwork.bean.Article;
import com.aeo.artwork.bean.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ArtworkDao {

    @Select("Select id, title, summary, cover, author, time from article where type = #{type} limit #{limit} offset #{offset}")
    ArrayList<ArticleInfo> getArticleList(@Param("type") Integer type, @Param("limit")Integer limit, @Param("offset")Integer offset);

    @Select("Select id, title, summary, cover, author, time from article where id = #{queryId} and type = #{type} " +
            "limit #{limit} offset #{offset}")
    ArrayList<ArticleInfo> getArticleListByQueryID(@Param("queryId") Integer queryId, @Param("type") Integer type, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Select("Select id, title, summary, cover, author, time from article where title like concat('%', #{queryTitle}, '%') " +
            "and type = #{type} limit #{limit} offset #{offset}")
    ArrayList<ArticleInfo> getArticleListByQueryTitle(@Param("queryTitle") String queryTitle, @Param("type") Integer type, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Select("Select count(*) from article where title like concat('%', #{queryTitle}, '%') and type = #{type}")
    Integer getArticleNumByQueryTitle(@Param("queryTitle") String queryTitle, @Param("type") Integer type);

    @Select("Select count(*) from article where type = #{type}")
    Integer getArticleNum(@Param("type") Integer type);

    @Select("Select * from article where id = #{id}")
    Article getArticleDetail(Integer id);
}
