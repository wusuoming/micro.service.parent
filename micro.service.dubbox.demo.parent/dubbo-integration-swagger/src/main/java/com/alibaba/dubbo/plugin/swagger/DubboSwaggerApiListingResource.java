package com.alibaba.dubbo.plugin.swagger;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import io.swagger.jaxrs.listing.BaseApiListingResource;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class DubboSwaggerApiListingResource extends BaseApiListingResource implements DubboSwaggerService {


    @Override
    public Response getListingJson() {
        Response response = getListingJsonResponse(null, null, null, null, null);

        response = Response.ok(DubboSwaggerUtils.EXCLUDE_EMPTY.toJson(response.getEntity()), MediaType.APPLICATION_JSON_TYPE).build();
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers", "x-requested-with, ssi-token");
        response.getHeaders().add("Access-Control-Max-Age", "3600");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        return response;
    }


    @Override
    public Response html(String fileName, String fileType) {
        return getFileResponse("/swagger/" + fileName + "." + fileType);
    }

    @Override
    public Response file(String dir, String fileName, String fileType) {
        return getFileResponse("/swagger/" + dir + "/" + fileName + "." + fileType);
    }

    private Response getFileResponse(String filePath) {
        Long lastModified = RpcContext.getContext().getRequest(HttpServletRequest.class).getDateHeader("If-Modified-Since");
        try {
            File file = new File(getClass().getResource(filePath).getFile());
            Long lastModified2 = file.lastModified();
            if (lastModified2 <= lastModified) {
                return Response.notModified().build();
            } else {
                Response response = Response.ok().entity(FileCopyUtils.copyToByteArray(file)).build();
                response.getHeaders().add("Last-Modified", new Date(lastModified2));
                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.noContent().build();
        }
    }


}