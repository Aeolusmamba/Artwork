package com.aeo.artwork.controller;

import com.aeo.artwork.bean.Result;
import com.aeo.artwork.service.BackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class BackendController {
    @Autowired
    BackendService backendService;

    @PostMapping("/artwork/backend/login")
    public Result login(@RequestBody Map<String, String> map, HttpServletRequest httpServletRequest){
        return backendService.login(map.get("username"), map.get("password"), httpServletRequest);
    }

    @PostMapping("/artwork/backend/publish")
    public Result publish(@RequestBody Map<String, Object> requestMap){
        return backendService.publish(requestMap);
    }

    @DeleteMapping("/artwork/backend/{id}/deleteArticle")
    public Result deleteArticle(@PathVariable("id") Integer id){
        return backendService.deleteArticle(id);
    }

    @PostMapping("/artwork/backend/editArticle")
    public Result editArticle(@RequestBody Map<String, Object> requestMap){
        return backendService.editArticle(requestMap);
    }

}
