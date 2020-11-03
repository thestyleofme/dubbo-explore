package com.github.thestyleofme.dubbo.consumer;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import com.github.thestyleofme.dubbo.consumer.bean.ConsumerDemo;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/28 1:14
 * @since 1.0.0
 */
public class ConsumerApplication {

    public static void main(String[] args) throws IOException {
        // AnnotationConfigApplicationContext context =
        //         new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        context.start();
        ConsumerDemo consumerDemo = context.getBean(ConsumerDemo.class);
        while (true) {
            System.in.read();
            // String result = consumerDemo.sayHello("world", 2000L);
            // System.out.println("result: " + result);

            CompletableFuture<String> future = RpcContext.getContext().asyncCall(
                    () -> consumerDemo.sayHello("world", 2000L));

            // CompletableFuture<String> future = consumerDemo.sayHelloAsync("world", 2000L);
            future.whenComplete((retValue, exception) -> {
                if (exception == null) {
                    System.out.println("async result: " + retValue);
                } else {
                    exception.printStackTrace();
                }
            });
        }
    }

    /*@Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(basePackages = {
            "com.github.thestyleofme.dubbo.consumer"
    })
    @EnableDubbo
    public static class ConsumerConfiguration {

    }*/
}

