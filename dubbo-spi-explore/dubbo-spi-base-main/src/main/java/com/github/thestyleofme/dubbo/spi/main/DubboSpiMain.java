package com.github.thestyleofme.dubbo.spi.main;

import java.util.Set;

import com.github.thestyleofme.dubbo.spi.service.HelloService;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/30 1:12
 * @since 1.0.0
 */
public class DubboSpiMain {

    public static void main(String[] args) {
        // 获取扩展加载器
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        // 遍历所有的支持的扩展点 META-INF.dubbo
        Set<String> extensions = extensionLoader.getSupportedExtensions();
        for (String extension : extensions) {
            HelloService helloService = extensionLoader.getExtension(extension);
            System.out.println(helloService.sayHello());
        }
    }
}
