package micro.service.dubbox.demo.filter;

import com.alibaba.dubbo.rpc.*;

import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().getResponse(HttpServletResponse.class).addHeader("Access-Control-Allow-Origin", "*");
        RpcContext.getContext().getResponse(HttpServletResponse.class).addHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with,Authorization,Access-Control-Allow-Origin");
        RpcContext.getContext().getResponse(HttpServletResponse.class).addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        RpcContext.getContext().getResponse(HttpServletResponse.class).addHeader("Access-Control-Max-Age", "360");
        return invoker.invoke(invocation);
    }
}