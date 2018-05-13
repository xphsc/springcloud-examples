package com.xphsc.ribbon.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 *  使用@RibbonClient ，为特定name的 Ribbon Client自定义配置
 * 使用@RibbonClient的configuration属性指定Ribbon的配置类
 * Created by ${huipei.x} on 2018-4-25.
 */
@RibbonClient(name = "user-server", configuration = RibbonConfig.class)
public class TestConfiguration {
}
