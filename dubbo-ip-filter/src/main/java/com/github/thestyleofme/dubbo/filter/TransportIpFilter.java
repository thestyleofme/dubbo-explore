package com.github.thestyleofme.dubbo.filter;

import com.github.thestyleofme.dubbo.filter.context.IpThreadLocal;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/08 22:08
 * @since 1.0.0
 */
@Activate(group = {CommonConstants.CONSUMER})
public class TransportIpFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        String ip = IpThreadLocal.getIp();
        invocation.getAttachments().put("requestIp", ip);
        return invoker.invoke(invocation);
    }
}
