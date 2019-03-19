package com.skymall.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.skymall.vo.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admin/oss")
public class OssController {
    @Value("${accessKey}")
    String accessKey;
    @Value("${secertKey}")
    String secretKey;
    @Value("${bucket}")
    String bucket;
    @Value("${expireSeconds}")
    long expireSeconds;

    @RequestMapping(value="/policy", method = RequestMethod.GET)
    public Object policy(){

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        String authToken = auth.uploadToken(bucket,null, expireSeconds, putPolicy);
        Map<String, String> map = new HashMap();
        map.put("key", UUID.randomUUID().toString());
        map.put("token",authToken);
        return new CommonResult().success(map);

    }
}
