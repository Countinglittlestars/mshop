package com.skymall.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skymall.annotation.IgnoreAuth;
import com.skymall.bo.TokenEntity;
import com.skymall.constant.WcConstant;
import com.skymall.domain.User;
import com.skymall.exception.ApiRRException;
import com.skymall.service.IWcUserService;
import com.skymall.utils.GuavaCacheUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限(Token)验证
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:38
 */
@EnableWebMvc
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    Logger logger = (Logger) LoggerFactory.getLogger(AuthorizationInterceptor.class);
    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String LOGIN_TOKEN_KEY = "X-Nideshop-Token";


    @Autowired
    IWcUserService wcUserService;





    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-Nideshop-Token,X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new ApiRRException("请先登录", 401);
        }

        //查询token信息
        TokenEntity tokenEntity = (TokenEntity) GuavaCacheUtil.getKey(token);
        if(tokenEntity == null){
            throw new ApiRRException("token失效，请重新登陆", 401);
        }
//        TokenEntity tokenEntity = tokenService.queryByToken(token);
//        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
//            throw new ApiRRException("token失效，请重新登录", 401);
//        }

        //设置userId到request里
        //并获取用户 user信息

        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());
        Integer id = tokenEntity.getUserId().intValue();
        User user = wcUserService.getById(id);
        request.setAttribute(WcConstant.GET_USER_KEY, user);
        return true;
    }



}
