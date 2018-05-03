package com.xphsc.feign.feign;

import com.xphsc.feign.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ${huipei.x} on 2018-4-25.
 */
@FeignClient("user-server")
public interface UserExtendsFeign extends UserService {


}
