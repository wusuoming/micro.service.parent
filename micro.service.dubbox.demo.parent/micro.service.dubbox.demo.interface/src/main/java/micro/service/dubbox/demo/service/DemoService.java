package micro.service.dubbox.demo.service;


import micro.service.dubbox.demo.dto.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface DemoService {
    @GET
    @Path("{uid}")
    public User sayHello(@PathParam("uid")String userId);
}