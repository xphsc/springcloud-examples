package com.xphsc.user.service.impl;

import com.xphsc.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Service
public class UserServiceImpl implements UserService {

    public String queryString(){
        return "这是一个用户服务接口";
    }


    public Object queryById(Integer id){
        return id;
    }
}
