package micro.service.dubbox.demo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import micro.service.dubbox.demo.service.IpWhiteList;
import micro.service.dubbox.demo.service.impl.IpWhiteListImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static micro.service.dubbox.demo.common.Constants.SCAN_PACKAGE;

@Configuration
@PropertySource("classpath:dubbo.properties")
public class DubboConfig {

    @Value("${dubbo.application.name}")
    private String dubboApplicationName;


    @Value("${dubbo.registry.address}")
    private String dubboRegistryAddress;

    /**
     * 由《dubbo:application》转换过来
     **/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger("slf4j");
        applicationConfig.setName(dubboApplicationName);
        return applicationConfig;
    }

    /**
     * 与<dubbo:annotation/>相当.提供方扫描带有@com.alibaba.dubbo.init.annotation.Reference的注解类
     */
    @Bean
    public static AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(SCAN_PACKAGE);//多个包可使用英文逗号隔开
        return annotationBean;
    }

    /**
     * 与<dubbo:registry/>相当
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboRegistryAddress);
        return registryConfig;
    }

    /**
     * 与<dubbo:protocol/>相当
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig("rest", 9090);
        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
        protocolConfig.setExtension("micro.service.dubbox.demo.config.ExceptionHandler");
        return protocolConfig;
    }
//
//    @Bean
//    public ProtocolConfig protocolConfig2() {
//        ProtocolConfig protocolConfig = new ProtocolConfig("rest", 9091);
//        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
//        return protocolConfig;
//    }
//
//    @Bean
//    public ProtocolConfig protocolConfig3() {
//        ProtocolConfig protocolConfig = new ProtocolConfig("rest", 9092);
//        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
//        return protocolConfig;
//    }
//
//    @Bean
//    public ProtocolConfig protocolConfig4() {
//        ProtocolConfig protocolConfig = new ProtocolConfig("rest", 9093);
//        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
//        return protocolConfig;
//    }
//
//    @Bean
//    public ProtocolConfig protocolConfig5() {
//        ProtocolConfig protocolConfig = new ProtocolConfig("rest", 9094);
//        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
//        return protocolConfig;
//    }
//    @Bean
//    public ProtocolConfig protocolConfig2() {
//        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
//        protocolConfig.setSerialization("kryo");//默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
//        return protocolConfig;
//    }


    @Bean
    public IpWhiteList ipWhiteList() {
        return new IpWhiteListImpl();
    }

}