package com.xphsc.feign.hystrix;

import com.xphsc.feign.feign.UserHystrixFeignFallbackFactory;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@Component
public  class UserHystrixClientFallbackFactory implements FallbackFactory<UserHystrixFeignFallbackFactory> {


    @Override
    public UserHystrixFeignFallbackFactory create(Throwable throwable) {
        return new UserHystrixFeignFallbackFactory() {
            @Override
            public String queryString() {
                return "queryString方法故障，启用断路器";
            }
        };
    }
}
