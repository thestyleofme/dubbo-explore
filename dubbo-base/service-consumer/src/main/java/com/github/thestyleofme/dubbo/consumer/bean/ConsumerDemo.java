package com.github.thestyleofme.dubbo.consumer.bean;

import java.util.concurrent.CompletableFuture;

import com.github.thestyleofme.dubbo.api.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/28 1:08
 * @since 1.0.0
 */
@Component
public class ConsumerDemo {

    @DubboReference(loadbalance = "roundrobin")
    // @DubboReference(loadbalance = "onlyFirst")
    private HelloService helloService;

    public String sayHello(String name, Long timeToWait) {
        return helloService.sayHello(name, timeToWait);
    }

    public CompletableFuture<String> sayHelloAsync(String name, Long timeToWait) {
        return helloService.sayHelloAsync(name, timeToWait);
    }
}
