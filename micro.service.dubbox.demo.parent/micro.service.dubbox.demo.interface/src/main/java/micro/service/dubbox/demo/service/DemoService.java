package micro.service.dubbox.demo.service;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import micro.service.dubbox.demo.dto.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Api("DemoService")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@Path("user")
public interface DemoService {
    @GET
    @Path("{uid}")
    @ApiOperation("sayHello")
    public User sayHello(@PathParam("uid") String userId);
}