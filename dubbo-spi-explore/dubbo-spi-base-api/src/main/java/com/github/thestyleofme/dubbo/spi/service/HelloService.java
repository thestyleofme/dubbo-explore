package com.github.thestyleofme.dubbo.spi.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/30 1:06
 * @since 1.0.0
 */
@SPI("dog")
public interface HelloService {

    /**
     * sayHello
     *
     * @return String
     */
    String sayHello();

    /**
     * sayHello
     *
     * @param url URL
     * @return String
     */
    @Adaptive
    String sayHello(URL url);
}
