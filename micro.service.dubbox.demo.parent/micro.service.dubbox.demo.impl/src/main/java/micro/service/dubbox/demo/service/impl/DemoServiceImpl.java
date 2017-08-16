package micro.service.dubbox.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import micro.service.dubbox.demo.dto.User;
import micro.service.dubbox.demo.service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

@RestController
@RequestMapping("user")
@Path("user")
@Service(group = "demo", version = "1.0.0", filter = {"authorityFilter", "corsFilter"})
public class DemoServiceImpl implements DemoService {

    @RequestMapping(value = "{uid}", method = RequestMethod.GET)
    public User sayHello(@PathVariable("uid") String userId) {
        System.out.println("input param uid :" + userId);
        User user = new User();
        user.setUserId(userId);
        user.setUsername("xbz");
        user.setPassword("1215xubin");
        user.setSex("男");
        user.setAge(35);
        return user;
    }

}