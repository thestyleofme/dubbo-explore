package com.github.thestyleofme.dubbo.spi.service.impl;

import com.github.thestyleofme.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.URL;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/30 1:09
 * @since 1.0.0
 */
public class DogHelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        return "barking";
    }

    @Override
    public String sayHello(URL url) {
        return "barking url";
    }
}
