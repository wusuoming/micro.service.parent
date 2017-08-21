package micro.service.dubbox.demo.filter;


import com.alibaba.dubbo.rpc.*;
import micro.service.dubbox.demo.service.IpWhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AuthorityFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityFilter.class);

    private IpWhiteList ipWhiteList;

    //dubbo通过setter方式自动注入  
    public void setIpWhiteList(IpWhiteList ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (!ipWhiteList.isEnabled()) {
            LOGGER.debug("白名单禁用");
            return invoker.invoke(invocation);
        }

        String clientIp = RpcContext.getContext().getRemoteHost();
        LOGGER.debug("访问ip为{}", clientIp);
        List<String> allowedIps = ipWhiteList.getAllowedIps();
        if (allowedIps.contains(clientIp)) {
            return invoker.invoke(invocation);
        } else {
            return new RpcResult(new RpcException(100001, "禁用的IP"));
        }
    }
}  