package com.aeo.artwork.service;

import com.aeo.artwork.bean.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BackendService {

    Result login(String username, String password, HttpServletRequest httpServletRequest);

    Result publish(Map<String, Object> requestMap);

    Result deleteArticle(Integer id);

    Result editArticle(Map<String, Object> requestMap);
}
