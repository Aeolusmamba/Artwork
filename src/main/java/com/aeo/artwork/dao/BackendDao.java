package com.aeo.artwork.dao;

import com.aeo.artwork.bean.Article;
import com.aeo.artwork.bean.Commodity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BackendDao {

    @Select("Select count(*) from user where username = #{username} and password = #{password}")
    Integer login(@Param("username") String username, @Param("password") String password);

    @Insert("Insert into article (title, summary, cover, author, type, content, time) " +
            "values (#{title}, #{summary}, #{cover}, #{author}, #{type}, #{content}, #{time})")
    void publish(Article article);

    @Delete("Delete from article where id = #{id}")
    Integer deleteArticle(Integer id);

    @Update("Update article set title=#{title}, summary=#{summary}, cover=#{cover}, author=#{author}, content=#{content}, " +
            "time=#{time} where id=#{id}")
    void editArticle(Article article);

    @Insert("Insert into commodity (name, cover, detail, link, price, time) " +
            "values (#{name}, #{cover}, #{detail}, #{link}, #{price}, #{time})")
    void publishGoods(Commodity commodity);

    @Delete("Delete from commodity where id = #{id}")
    Integer deleteGoods(Integer id);

    @Update("Update commodity set name=#{name}, cover=#{cover}, detail=#{detail}, link=#{link}, " +
            "price=#{price}, time=#{time} where id=#{id}")
    void editGoods(Commodity commodity);


    @Select("Select count(*) from commodity where id = #{id}")
    Integer countId(Integer id);
}
