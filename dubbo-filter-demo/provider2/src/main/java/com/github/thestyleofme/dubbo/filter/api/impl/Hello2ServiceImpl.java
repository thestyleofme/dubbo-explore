package com.github.thestyleofme.dubbo.filter.api.impl;

import com.github.thestyleofme.dubbo.filter.api.Hello2Service;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/08 22:33
 * @since 1.0.0
 */
@DubboService
public class Hello2ServiceImpl implements Hello2Service {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        String requestIp = RpcContext.getContext().getAttachment("requestIp");
        logger.info("hello2 requestIp: {}", requestIp);
        return "hello2 " + name;
    }
}
