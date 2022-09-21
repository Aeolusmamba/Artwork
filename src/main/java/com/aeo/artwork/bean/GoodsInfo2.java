package com.aeo.artwork.bean;

import com.alibaba.fastjson.JSONArray;

public class GoodsInfo2 {
    private Integer id;
    private String name;
    private JSONArray cover = new JSONArray();
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getCover() {
        return cover;
    }

    public void setCover(JSONArray cover) {
        this.cover = cover;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
