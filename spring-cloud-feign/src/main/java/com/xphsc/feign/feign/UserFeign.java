package com.xphsc.feign.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@FeignClient("user-server")
public interface UserFeign {

    //@GetMapping("/queryString")
    @RequestMapping(method = RequestMethod.GET, value ="/queryString")
    public String queryString();
}
