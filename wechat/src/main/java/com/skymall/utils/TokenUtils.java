package com.skymall.utils;

import com.skymall.bo.TokenEntity;
import com.skymall.constant.WcConstant;
import com.skymall.domain.User;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    @Value(value = "")
    Integer expire;

    public static Map<String, Object> createToken(User user){
//生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setUserId(user.getId().longValue());
        //直接添加进去
        GuavaCacheUtil.setKey(token, tokenEntity);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
