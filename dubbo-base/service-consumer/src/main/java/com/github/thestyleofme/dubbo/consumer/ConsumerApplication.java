package com.github.thestyleofme.dubbo.consumer;

import java.io.IOException;

import com.github.thestyleofme.dubbo.consumer.bean.ConsumerDemo;
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
        while(true){
            System.out.println(consumerDemo.sayHello("world"));
            System.in.read();
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

