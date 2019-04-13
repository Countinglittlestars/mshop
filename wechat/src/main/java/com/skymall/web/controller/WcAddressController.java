package com.skymall.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skymall.constant.WcConstant;
import com.skymall.domain.Address;
import com.skymall.service.IWcAddressService;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.AddressVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mshop/wc/address")
public class WcAddressController extends AbstractController{

    @Resource
    IWcAddressService addressService;

    /**
     * 获取用户的收货地址
     */
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @PostMapping("list")
    public Object list() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Map<String, Object> param = new HashMap<String, Object>();
        List<Address> addresses = addressService.list(new QueryWrapper<Address>().lambda().eq(Address::getUserId, userId));
        return Response.success(addresses);
    }


    /**
     * 获取收货地址的详情
     */
    @ApiOperation(value = "获取收货地址的详情", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "收获地址ID", required = true, dataType = "Integer")})
    @PostMapping("detail")
    public Object detail(Integer id) {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Address entity = addressService.getById(id);
        //判断越权行为
        if (!entity.getUserId().equals(userId)) {
            return Response.error(403,"您无权查看");
        }
        return Response.success(entity);
    }

    /**
     * 添加或更新收货地址
     */
    @ApiOperation(value = "添加或更新收货地址", response = Map.class)
    @PostMapping("save")
    public Object save() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject addressJson = this.getJsonRequest();
        Address entity = new Address();
        if (null != addressJson) {
            entity.setId(addressJson.getInteger("id"));
            entity.setUserId(userId);
            entity.setUserName(addressJson.getString("userName"));
            entity.setPostalCode(addressJson.getString("postalCode"));
            entity.setProvinceName(addressJson.getString("provinceName"));
            entity.setCityName(addressJson.getString("cityName"));
            entity.setCountyName(addressJson.getString("countyName"));
            entity.setDetailInfo(addressJson.getString("detailInfo"));
            entity.setNationalCode(addressJson.getString("nationalCode"));
            entity.setTelNumber(addressJson.getString("telNumber"));
            entity.setIsDefault(addressJson.getInteger("isDefault"));
        }
        if (null == entity.getId() || entity.getId() == 0) {
            entity.setId(null);
            addressService.save(entity);
        } else {
            addressService.updateById(entity);
        }
        return Response.success(entity);
    }





    /**
     * 删除指定的收货地址
     */
    @ApiOperation(value = "删除指定的收货地址", response = Map.class)
    @PostMapping("delete")
    public Object delete() {
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        JSONObject jsonParam = this.getJsonRequest();
        Integer id = jsonParam.getIntValue("id");

        Address entity = addressService.getById(id);
        //判断越权行为
        if (!entity.getUserId().equals(userId)) {
            return Response.error(403, "您无权删除");
        }
        addressService.removeById(id);
        return Response.success("");
    }









}
