package com.xphsc.feign.feign;

import com.xphsc.feign.hystrix.UserHystrixClientFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@FeignClient(value = "user-server" ,fallbackFactory = UserHystrixClientFallbackFactory.class)
public interface UserHystrixFeignFallbackFactory {
    @RequestMapping(method = RequestMethod.GET, value ="/queryString")
    public String queryString();
}
