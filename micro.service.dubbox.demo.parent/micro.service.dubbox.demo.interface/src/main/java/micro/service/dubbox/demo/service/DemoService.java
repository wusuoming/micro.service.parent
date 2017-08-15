package micro.service.dubbox.demo.service;


import micro.service.dubbox.demo.dto.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("user")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public interface DemoService {
    @GET
    @Path("{uid}")
    public User sayHello(@PathParam("uid") String userId);
}