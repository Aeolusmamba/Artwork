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

    @Select("Select id, title, summary, cover, author, time from article limit #{limit} offset #{offset}")
    ArrayList<ArticleInfo> getArticleList(@Param("limit")Integer limit, @Param("offset")Integer offset);

    @Select("Select id, title, summary, cover, author, time from article where id = #{queryId} limit #{limit} offset #{offset}")
    ArrayList<ArticleInfo> getArticleListByQueryID(@Param("queryId") Integer queryId, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Select("Select id, title, summary, cover, author, time from article where title like concat('%', #{queryTitle}, '%') " +
            "limit #{limit} offset #{offset}")
    ArrayList<ArticleInfo> getArticleListByQueryTitle(@Param("queryTitle") String queryTitle, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Select("Select count(*) from article where title like concat('%', #{queryTitle}, '%')")
    Integer getArticleNumByQueryTitle(@Param("queryTitle") String queryTitle);

    @Select("Select count(*) from article")
    Integer getArticleNum();

    @Select("Select * from article where id = #{id}")
    Article getArticleDetail(Integer id);
}
