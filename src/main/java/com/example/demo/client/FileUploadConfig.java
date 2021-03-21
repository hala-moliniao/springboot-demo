package com.example.demo.client;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * @program: cw-rhea
 * @description:
 * @author: 上传文件大小配置
 * @create: 2019-09-23 11:44
 **/
@Configuration
public class FileUploadConfig {
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件大小
        factory.setMaxFileSize(DataSize.of(2048L, DataUnit.MEGABYTES));
        // 请求体大小
        factory.setMaxRequestSize(DataSize.of(2048L, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
