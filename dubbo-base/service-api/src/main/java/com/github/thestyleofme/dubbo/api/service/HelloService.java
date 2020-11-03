package com.github.thestyleofme.dubbo.api.service;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/28 0:33
 * @since 1.0.0
 */
public interface HelloService {

    /**
     * sayHello
     *
     * @param name       name
     * @param timeToWait timeToWait
     * @return String
     */
    String sayHello(String name, Long timeToWait);

    /**
     * sayHelloAsync
     *
     * @param name       name
     * @param timeToWait timeToWait
     * @return String
     */
    CompletableFuture<String> sayHelloAsync(String name, Long timeToWait);
}
