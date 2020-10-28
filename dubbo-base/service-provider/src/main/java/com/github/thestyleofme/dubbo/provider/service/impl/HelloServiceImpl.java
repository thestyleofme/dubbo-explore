package com.github.thestyleofme.dubbo.provider.service.impl;

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
    public String sayHello(String name) {
        try {
            // 模仿接口超时
            TimeUnit.SECONDS.sleep(2000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "hello, " + name;
    }
}
