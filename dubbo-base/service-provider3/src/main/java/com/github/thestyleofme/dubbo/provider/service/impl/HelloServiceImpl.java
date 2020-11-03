package com.github.thestyleofme.dubbo.provider.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.github.thestyleofme.dubbo.api.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/28 0:43
 * @since 1.0.0
 */
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name, Long timeToWait) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeToWait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "hello3, " + name;
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name, Long timeToWait) {
        // 建议为supplyAsync提供自定义线程池，避免使用JDK公用线程池
        return CompletableFuture.supplyAsync(() -> sayHello(name, timeToWait));
    }
}
