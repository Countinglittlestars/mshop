package com.skymall.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.skymall.annotation.IgnoreAuth;
import com.skymall.bo.FullUserInfo;
import com.skymall.bo.UserInfo;
import com.skymall.domain.User;
import com.skymall.service.IWcAuthService;
import com.skymall.service.IWcUserService;
import com.skymall.utils.*;
import com.skymall.vo.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "mshop/wc/auth")
public class WcAuthController extends AbstractController {
    Logger logger = LoggerFactory.getLogger(WcAuthController.class);

    @Resource
    IWcUserService wcUserService;

    @Resource
    IWcAuthService wcAuthService;

    @IgnoreAuth
    @RequestMapping(value = "/loginByWeChat")
    public Response loginByWeChat(){
        JSONObject jsonParam = this.getJsonRequest();
        FullUserInfo fullUserInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);
        }
        if (null == fullUserInfo) {
            return Response.error("登录失败");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        UserInfo userInfo = fullUserInfo.getUserInfo();

        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        logger.info("》》》组合token为：" + requestUrl);
        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return Response.error("登录失败");
        }
        //验证用户信息完整性
        String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
        if (!fullUserInfo.getSignature().equals(sha1)) {
            return Response.error("登录失败");
        }
        Date nowTime = new Date();
        User userVo = wcUserService.getByOpenId(sessionData.getString("openid"));
        if (null == userVo) {
            userVo = new User();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(sessionData.getString("openid"));
            userVo.setRegisterTime(nowTime);
            userVo.setWeixinOpenid(sessionData.getString("openid"));
            userVo.setAvatar(userInfo.getAvatarUrl());
            userVo.setGender(userInfo.getGender()); // //性别 0：未知、1：男、2：女
            userVo.setNickname(userInfo.getNickName());
            wcUserService.save(userVo);
        } else {
//            userVo.setLast_login_ip(this.getClientIp());
//            userVo.setLast_login_time(nowTime);
            wcUserService.update(userVo);
        }

        Map<String, Object> tokenMap = TokenUtils.createToken(userVo);
        String token = MapUtils.getString(tokenMap, "token");

        if (null == userInfo || StringUtils.isNullOrEmpty(token)) {
            return Response.error("登录失败");
        }

        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userVo.getId());
        return Response.success(resultObj);
    }











}
