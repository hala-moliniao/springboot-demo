package com.example.demo.annotation;

import com.example.demo.client.NettyRpcClientRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: created by yckj2786
 * @date: 2021/2/26
 * @description: TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(NettyRpcClientRegistrar.class)
public @interface EnableNettyRpcClient {

    //扫描的包名，如果为空，则根据启动类所在的包名扫描
    String[] basePackages() default {};

    //扫描的字节码类型，可根据字节码类型获取对应的包路径
    Class<?>[] basePackageClasses() default {};
}
