package com.xphsc.user.controller;

import com.xphsc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@RestController
public class UserController {
   @Autowired
    private UserService userService;

    @GetMapping("/queryString")
    public String queryString(){
        return userService.queryString();
    }

    @GetMapping("/queryById/{id}")
    public Object queryById(@PathVariable("id") Integer id){
        return userService.queryById(id);
    }

}
