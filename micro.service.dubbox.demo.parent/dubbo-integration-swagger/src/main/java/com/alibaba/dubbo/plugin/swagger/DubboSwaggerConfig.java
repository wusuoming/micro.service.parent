package com.alibaba.dubbo.plugin.swagger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

@Configuration
public class DubboSwaggerConfig implements ImportBeanDefinitionRegistrar {

    private String BEAN_CONFIG_BEAN_NAME = "beanConfig";
    private String ANNOTATION_BEAN_NAME = "annotationSwaggerBean";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        registerAnnotationSwaggerBean(annotationMetadata, beanDefinitionRegistry);
        registerBeanConfig(annotationMetadata, beanDefinitionRegistry);

    }

    private void registerBeanConfig(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (!beanDefinitionRegistry.containsBeanDefinition(BEAN_CONFIG_BEAN_NAME)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                    .genericBeanDefinition(BeanConfig.class);
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                    annotationMetadata.getAnnotationAttributes(EnableDubboSwagger.class.getName()));
            for (Map.Entry<String, Object> stringObjectEntry : attributes.entrySet()) {
                String key = stringObjectEntry.getKey();
                Object value = stringObjectEntry.getValue();
                if (value != null && !(value instanceof String && StringUtils.isBlank((String) value)))
                    beanDefinitionBuilder.addPropertyValue(key, value);
            }
            beanDefinitionRegistry.registerBeanDefinition(BEAN_CONFIG_BEAN_NAME, beanDefinitionBuilder.getBeanDefinition());
        }
    }

    private void registerAnnotationSwaggerBean(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (!beanDefinitionRegistry.containsBeanDefinition(ANNOTATION_BEAN_NAME)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(AnnotationBean.class);
            beanDefinitionBuilder.addPropertyValue("package", "com.alibaba.dubbo.plugin.swagger");
            beanDefinitionRegistry.registerBeanDefinition(ANNOTATION_BEAN_NAME, beanDefinitionBuilder.getBeanDefinition());
        }

    }
}
