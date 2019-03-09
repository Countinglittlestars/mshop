package com.skymall.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.skymall.constant.WcConstant;
import com.skymall.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 获取reqest的信息，并取得其成Json 格式
     * @return
     */
    public JSONObject getJsonRequest() {
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
            result = JSONObject.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public User getUser(HttpServletRequest request){
        return (User) request.getAttribute(WcConstant.GET_USER_KEY);
    }

}
