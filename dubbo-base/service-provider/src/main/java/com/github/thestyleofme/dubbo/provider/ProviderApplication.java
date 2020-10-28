package com.github.thestyleofme.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/28 0:49
 * @since 1.0.0
 */
public class ProviderApplication {

    public static void main(String[] args) throws IOException {
        // AnnotationConfigApplicationContext context =
        //         new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml");
        context.start();
        // 按任意键退出
        System.in.read();
    }

    /*@Configuration
    @EnableDubbo(scanBasePackages = "com.github.thestyleofme.dubbo.provider")
    @PropertySource("classpath:/dubbo-provider.properties")
    public static class ProviderConfiguration {

        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress("zookeeper://127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183?timeout=10000");
            return registryConfig;
        }
    }*/
}
