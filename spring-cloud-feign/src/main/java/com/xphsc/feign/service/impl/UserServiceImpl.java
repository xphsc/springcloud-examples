package com.xphsc.feign.service.impl;

import com.xphsc.feign.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Object queryById(@PathVariable("id") Integer id) {
          return "queryById方法故障，启用断路器";
    }
}
