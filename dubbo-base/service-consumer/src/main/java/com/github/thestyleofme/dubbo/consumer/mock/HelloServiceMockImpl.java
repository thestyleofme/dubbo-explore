package com.github.thestyleofme.dubbo.consumer.mock;

import com.github.thestyleofme.dubbo.api.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/28 23:30
 * @since 1.0.0
 */
@DubboService
public class HelloServiceMockImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello mock";
    }
}
