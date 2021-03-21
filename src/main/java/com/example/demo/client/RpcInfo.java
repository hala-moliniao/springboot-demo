package com.example.demo.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: created by yckj2786
 * @date: 2021/2/26
 * @description: TODO
 */
@Data
public class RpcInfo implements Serializable {

    /**
     * 调用服务的接口名
     */
    private String className;
    /**
     * 调用服务的方法名
     */
    private String methodName;
    /**
     * 调用方法的参数列表类型
     */
    private Class[] paramTypes;
    /**
     * 调用服务传参
     */
    private Object[] params;
}
