package com.alibaba.dubbo.plugin.swagger;

import com.alibaba.dubbo.common.utils.StringUtils;
import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

@Configuration
public class AnnotationBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private String BEAN_NAME = "beanConfig";

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registry.getBeanDefinition(BEAN_NAME).getSource();
        if (!registry.containsBeanDefinition(BEAN_NAME)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                    .genericBeanDefinition(BeanConfig.class);
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                    importingClassMetadata.getAnnotationAttributes(EnableDubboSwagger.class.getName()));
            for (Map.Entry<String, Object> stringObjectEntry : attributes.entrySet()) {
                String key = stringObjectEntry.getKey();
                Object value = stringObjectEntry.getValue();
                if (value != null && !(value instanceof String && StringUtils.isBlank((String) value)))
                    beanDefinitionBuilder.addPropertyValue(key, value);
            }
            registry.registerBeanDefinition(BEAN_NAME, beanDefinitionBuilder.getBeanDefinition());
        }
    }
}