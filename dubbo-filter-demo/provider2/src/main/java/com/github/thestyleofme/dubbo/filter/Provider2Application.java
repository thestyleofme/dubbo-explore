package com.github.thestyleofme.dubbo.filter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/08 23:21
 * @since 1.0.0
 */
@EnableAutoConfiguration
public class Provider2Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Provider2Application.class).run(args);
    }
}
