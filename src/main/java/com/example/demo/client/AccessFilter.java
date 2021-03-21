//package com.example.demo.client;
//
//import cn.cloudwalk.cloud.result.CloudwalkResult;
//import cn.cloudwalk.security.gateway.common.ErrorEnum;
//import cn.cloudwalk.security.gateway.dto.result.AccountInfoResult;
//import cn.cloudwalk.security.gateway.exception.SystemException;
//import cn.cloudwalk.security.gateway.service.AuthService;
//import com.alibaba.fastjson.JSON;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//
///**
// * @Description
// * @Author YCKJ2765
// * @Date 2020/3/17 17:55
// */
//@Slf4j
//@Configuration
//public class AccessFilter extends ZuulFilter {
//
//    private static final String TOKEN = "Authorization";
//
//    @Autowired
//    private AuthService authService;
//
//    @Value("${zuul.routes.ignoredPatterns}")
//    private String ignorePath;
//
//    @Value("${zuul.routes.authPatterns}")
//    private String authPath;
//
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public CloudwalkResult<String> run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST,GET");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,Authorization");
//        String url = request.getRequestURI();
//        if (RequestMethod.POST.toString().equals(request.getMethod()) || RequestMethod.GET.toString().equals(request.getMethod())) {
//            List<String> ignoreUrlList = new ArrayList<>(Arrays.asList(ignorePath.split(" ")));
//            if (ignoreUrlList.contains(url)) {
//                return null;
//            }
//        }
//        String accessToken = request.getHeader(TOKEN);
//        if (StringUtils.isBlank(accessToken)) {
//            sendResult(CloudwalkResult.fail(ErrorEnum.TOKEN_EXPIRE.getCode(), ErrorEnum.TOKEN_EXPIRE.getMsg()), response);
//        }
//        AccountInfoResult userInfo = authService.getAccountInfo(accessToken);
//        if (Objects.equals(null, userInfo)) {
//            sendResult(CloudwalkResult.fail(ErrorEnum.TOKEN_EXPIRE.getCode(), ErrorEnum.TOKEN_EXPIRE.getMsg()), response);
//        }
//        // 验证菜单权限
//        List<String> authUrlList = new ArrayList<>(Arrays.asList(authPath.split(" ")));
//        log.info("菜单权限验证===>:{}",url);
//        if(!authUrlList.contains(url)) {
//            Boolean isHasAuth = authService.validateAccountAuth(userInfo.getRoleId(), url);
//            if (!isHasAuth) {
//                sendResult(CloudwalkResult.fail(ErrorEnum.NO_AUTH_OPERATE_ERROR.getCode(), ErrorEnum.NO_AUTH_OPERATE_ERROR.getMsg()), response);
//            }
//        }
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (parameterMap == null) {
//            return null;
//        }
//        // 替换,业务逻辑
//        Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
//        if (requestQueryParams == null) {
//            requestQueryParams = new HashMap<>(parameterMap.size() * 2);
//        }
//
//        for (String key : parameterMap.keySet()) {
//            String[] values = parameterMap.get(key);
//            List<String> valueList = new LinkedList<>(Arrays.asList(values));
//            requestQueryParams.put(key, valueList);
//        }
//
//        // 重新写入参数
//        ctx.addZuulRequestHeader("token", accessToken);
//        ctx.setRequestQueryParams(requestQueryParams);
//        log.info("转译完成, url = {}", request.getRequestURI());
//        return null;
//    }
//
//    protected void sendResult(CloudwalkResult<String> result, ServletResponse response) {
//        response.setContentType("application/json;charset=UTF-8");
//        ServletOutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            out.write(JSON.toJSONString(result).getBytes());
//        } catch (IOException e) {
//            log.error("账号登录异常:{},堆栈信息:{}", e.getMessage(), e);
//            throw new SystemException(ErrorEnum.SYSTEM_ERROR.getCode(), ErrorEnum.SYSTEM_ERROR.getMsg());
//        } finally {
//            try {
//                out.flush();
//                out.close();
//            } catch (IOException e) {
//                log.error("系统关闭流异常:{},堆栈信息:{}", e.getMessage(), e);
//            }
//        }
//    }
//}
