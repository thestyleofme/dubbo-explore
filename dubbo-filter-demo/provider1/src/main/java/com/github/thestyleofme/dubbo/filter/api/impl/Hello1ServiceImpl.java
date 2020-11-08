package com.github.thestyleofme.dubbo.filter.api.impl;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.github.thestyleofme.dubbo.filter.api.Hello1Service;
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
public class Hello1ServiceImpl implements Hello1Service {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        String requestIp = RpcContext.getContext().getAttachment("requestIp");
        logger.info("hello1 requestIp: {}", requestIp);
        return "hello1 " + name;
    }

    @Override
    public String sayHello1(String name) {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "sayHello1 " + name;
    }

    @Override
    public String sayHello2(String name) {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "sayHello2 " + name;
    }

    @Override
    public String sayHello3(String name) {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "sayHello3 " + name;
    }
}
