package com.xphsc.feign.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value ="/queryById/{id}")
    Object queryById(@PathVariable("id") Integer id);
}
