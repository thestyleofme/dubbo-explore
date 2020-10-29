package com.github.thestyleofme.dubbo.spi.main;

import com.github.thestyleofme.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/30 1:17
 * @since 1.0.0
 */
public class DubboAdaptiveMain {

    public static void main(String[] args) {
        // URL url = URL.valueOf("test://localhost/hello?hello.service=dog");
        URL url = URL.valueOf("test://localhost/hello");
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        HelloService helloService = extensionLoader.getAdaptiveExtension();
        System.out.println(helloService.sayHello(url));
    }

}
