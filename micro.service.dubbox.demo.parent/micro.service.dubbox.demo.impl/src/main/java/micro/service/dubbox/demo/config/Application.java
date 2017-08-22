package micro.service.dubbox.demo.config;

import com.alibaba.dubbo.plugin.swagger.EnableDubboSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboSwagger(resourcePackage = "micro.service.dubbox.demo.service", title = "标题", version = "1.0.1", description = "描述")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}