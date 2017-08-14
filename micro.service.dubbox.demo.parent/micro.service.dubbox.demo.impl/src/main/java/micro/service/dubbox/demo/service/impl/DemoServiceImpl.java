package micro.service.dubbox.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import micro.service.dubbox.demo.dto.User;
import micro.service.dubbox.demo.service.DemoService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Service(group = "demo", version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    @GET
    @Path("{uid}")
    public User sayHello(@PathParam("uid") String userId) {
        System.out.println("input param uid :" + userId);
        User user = new User();
        user.setUsername("xbz");
        user.setPassword("1215xubin");
        return user;
    }

}