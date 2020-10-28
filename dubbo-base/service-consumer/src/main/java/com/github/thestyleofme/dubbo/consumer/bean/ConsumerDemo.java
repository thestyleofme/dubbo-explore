package com.github.thestyleofme.dubbo.consumer.bean;

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

    @DubboReference
    private HelloService helloService;

    public String sayHello(String name){
       return helloService.sayHello(name);
    }
}
