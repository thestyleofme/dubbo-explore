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
public class Provider1Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Provider1Application.class).run(args);
    }
}
