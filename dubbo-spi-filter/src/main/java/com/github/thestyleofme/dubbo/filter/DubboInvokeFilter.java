package com.github.thestyleofme.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboInvokeFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        try {
            return invoker.invoke(invocation);
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("invoke cost: {}ms", (end - start));
        }
    }
}
