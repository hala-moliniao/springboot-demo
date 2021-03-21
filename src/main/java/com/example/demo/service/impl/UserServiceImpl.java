package com.example.demo.service.impl;

import com.example.demo.service.UserService;

/**
 * @author: created by yckj2786
 * @date: 2021/2/26
 * @description: TODO
 */
public class UserServiceImpl implements UserService {
    @Override
    public String callRpc(String param) {
        System.out.println(param);
        return "The response is --> " + param;
    }
}
