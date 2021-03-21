package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.moliniao.starter.autoconfigure.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: created by yckj2786
 * @date: 2021/2/26
 * @description: TODO
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserService userService;

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/callRpc")
    public String callRpcTest(){
//        String message = userService.callRpc("callRpc execute......");
        String message = "userService";
        return helloService.helloMoliniao("moliniao") + message;
    }

    @RequestMapping(value = "/mess")
    public String messageSource(){
        String message = messageSource.getMessage("76012900", null, "", LocaleContextHolder.getLocale());
        return "ok==>" + message;
    }
}
