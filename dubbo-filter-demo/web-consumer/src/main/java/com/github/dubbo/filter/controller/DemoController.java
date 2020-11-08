package com.github.dubbo.filter.controller;

import java.util.concurrent.ExecutorService;
import javax.servlet.http.HttpServletRequest;

import com.github.dubbo.filter.utils.IpUtil;
import com.github.dubbo.filter.utils.ThreadPoolUtil;
import com.github.thestyleofme.dubbo.filter.api.Hello1Service;
import com.github.thestyleofme.dubbo.filter.api.Hello2Service;
import com.github.thestyleofme.dubbo.filter.context.IpThreadLocal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/08 23:01
 * @since 1.0.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @DubboReference
    private Hello1Service hello1Service;
    @DubboReference
    private Hello2Service hello2Service;

    private final ExecutorService executorService = ThreadPoolUtil.getExecutorService();

    @RequestMapping("/hello")
    public String sayHello(String name, HttpServletRequest request) {
        String remoteAddr = IpUtil.getIpAddress(request);
        IpThreadLocal.setIp(remoteAddr);
        try {
            String hello1 = hello1Service.sayHello(name);
            String hello2 = hello2Service.sayHello(name);
            logger.info("hello1: {}", hello1);
            logger.info("hello2: {}", hello2);
        } finally {
            IpThreadLocal.clear();
        }
        return "success";
    }

    @RequestMapping("/tp-monitor")
    public void tpMonitor(String name) {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            executorService.execute(() -> {
                while (true) {
                    hello1Service.sayHello1(name);
                    hello1Service.sayHello2(name);
                    hello1Service.sayHello3(name);
                }
            });
        }
    }

}
