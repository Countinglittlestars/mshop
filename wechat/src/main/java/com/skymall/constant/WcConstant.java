package com.skymall.constant;

public class WcConstant {
    //获取微信客户端传进后台的token对应的key
    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    //存储token对应的Key
    public static final String LOGIN_TOKEN_KEY = "X-Nideshop-Token";
    //存储用户信息对应的Key
    public static final String GET_USER_KEY = "GET_USER_KEY";
    //token的过期时间对应，觉得应该在配置文件里面配置，现在在加在常量这里
    private static final Integer EXPIRE = 3600 * 12;


}
