package com.alibaba.dubbo.plugin.swagger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("dubbo")
public interface DubboSwaggerService {

    @GET
    @Path("swagger")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getListingJson();

    @GET
    @Path("{fileName}.{fileType}")
    Response html(@PathParam("fileName") String fileName, @PathParam("fileType") String fileType);

    @GET
    @Path("/{dir}/{fileName}.{fileType}")
    Response file(@PathParam("dir") String dir, @PathParam("fileName") String fileName, @PathParam("fileType") String fileType);
}