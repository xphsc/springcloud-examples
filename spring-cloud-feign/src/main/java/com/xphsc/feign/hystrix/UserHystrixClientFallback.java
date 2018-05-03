package com.xphsc.feign.hystrix;

import com.xphsc.feign.feign.UserHystrixFeign;
import org.springframework.stereotype.Component;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Component
public class UserHystrixClientFallback implements UserHystrixFeign {
    @Override
    public String queryString() {
        return "queryString方法故障，启用断路器";
    }
}
