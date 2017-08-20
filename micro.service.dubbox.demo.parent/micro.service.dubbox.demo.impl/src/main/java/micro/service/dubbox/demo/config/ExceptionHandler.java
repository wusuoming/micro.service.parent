package micro.service.dubbox.demo.config;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.rest.RpcExceptionMapper;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import micro.service.dubbox.demo.exception.BaseException;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;


public class ExceptionHandler extends RpcExceptionMapper {

    public Response toResponse(RpcException e) {
        if (e.getCause() instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) e.getCause());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(BaseException.create(e.getCode(), e.getMessage())).type(ContentType.APPLICATION_JSON_UTF_8).build();
    }
}
