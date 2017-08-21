package com.alibaba.dubbo.plugin.swagger;

import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboSwaggerConfig {

    @Bean
    BeanConfig beanConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setDescription("hello");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setContact("wusm");
        beanConfig.setLicense("Apache 2.0");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/");
        beanConfig.setTitle("这个是标题啊");
        beanConfig.setResourcePackage("micro.service.dubbox.demo.service");
        beanConfig.setScan(true);
        return beanConfig;
    }
}
