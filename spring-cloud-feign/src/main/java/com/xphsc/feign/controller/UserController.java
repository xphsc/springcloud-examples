package com.xphsc.feign.controller;


import com.xphsc.feign.feign.UserExtendsFeign;
import com.xphsc.feign.feign.UserFeign;
import com.xphsc.feign.feign.UserHystrixFeign;
import com.xphsc.feign.feign.UserHystrixFeignFallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@RestController
public class UserController {
   @Autowired
    private UserFeign userFeign;
    @Autowired
    private UserExtendsFeign userExtendsFeign;
    @Autowired
    private UserHystrixFeign userHystrixFeign;
    @Autowired
    private UserHystrixFeignFallbackFactory userHystrixFeignFallbackFactory;
    @GetMapping("/queryString")
    public String queryString(){
        return userFeign.queryString();
    }

   @GetMapping("/queryById/{id}")
    public Object queryById(@PathVariable("id") Integer id){
        return userExtendsFeign.queryById(id);
    }

    @GetMapping("/queryStringByHystrix")
    public String queryStringHystrixFeign(){
        return userHystrixFeign.queryString();
    }

    @GetMapping("/queryStringHystriFactory")
    public String queryStringHystriFactory(){
        return userHystrixFeignFallbackFactory.queryString();
    }


}
