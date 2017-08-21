package com.alibaba.dubbo.plugin.swagger;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DubboSwaggerConfig.class})
public @interface EnableDubboSwagger {

    String resourcePackage();

    String[] schemes() default {"http"};

    String title();

    String version();

    String description();

    String contact() default "DubboSwagger";

    String license() default "Apache 2.0";

    String licenseUrl() default "http://www.apache.org/licenses/LICENSE-2.0.html";

    String filterClass() default "";


    String host() default "";

    String basePath() default "/";

    boolean scan() default true;
}
