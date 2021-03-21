package com.example.demo.service;

import com.example.demo.annotation.NettyRpcClient;

/**
 * @author: created by yckj2786
 * @date: 2021/2/26
 * @description: TODO
 */
@NettyRpcClient
public interface UserService {

    String callRpc(String param);
}
