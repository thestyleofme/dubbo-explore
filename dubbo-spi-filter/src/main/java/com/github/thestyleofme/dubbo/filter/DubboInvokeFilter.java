package com.github.thestyleofme.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/30 1:41
 * @since 1.0.0
 */
@Activate(group = {CommonConstants.CONSUMER, CommonConstants.PROVIDER})
public class DubboInvokeFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        try {
            return invoker.invoke(invocation);
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("invoke cost: " + (end - start) + "ms");
        }
    }
}
