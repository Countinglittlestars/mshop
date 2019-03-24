package com.skymall.controller;

import com.skymall.domain.SysUser;
import com.skymall.dto.UmsAdminLoginParam;
import com.skymall.dto.UmsAdminParam;
import com.skymall.service.ISysUserService;
import com.skymall.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Api(description = "登录管理")
@RestController
@RequestMapping(value = "/admin")
public class LoginController {

    @Resource
    ISysUserService userService;

    private String tokenHeader = "Authorization";

    private String tokenHead = "Bearer";

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        SysUser umsAdmin = userService.register(umsAdminParam);
        if (umsAdmin == null) {
            new CommonResult().failed();
        }
        return new CommonResult().success(umsAdmin);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = userService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return new CommonResult().validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }


    @ApiOperation(value = "注销")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout() {
        return new CommonResult().success(null);
    }

    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object getAdminInfo(Principal principal) {
        String username = principal.getName();
        SysUser sysUser = userService.getUserByUserName(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", sysUser.getUsername());
        data.put("roles", new String[]{"TEST"});
//        data.put("icon", sysUser.getIcon());
        return new CommonResult().success(data);
    }

}
