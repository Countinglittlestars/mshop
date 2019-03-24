package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.domain.User;
import com.skymall.dto.AddressAddDto;
import com.skymall.enums.ExceptionEnums;
import com.skymall.exception.ApiRRException;
import com.skymall.service.impl.AddressServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date 2019/3/14
 * 收货地址管理
 */

@Api(description = "收货地址管理")
@RestController
@RequestMapping("/admin/address")
public class AddressController {

    @Resource
    private AddressServiceImpl addressService;

    /**
     * 新增收货地址
     * @param addressAddDto
     */
    @ApiOperation(value = "新增收货地址")
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST )
    public Object addAddress(@RequestBody AddressAddDto addressAddDto){
        addressService.addAddress(addressAddDto);
        return new CommonResult().success();
    }

    /**
     * 根据Id修改收货地址
     * @param address
     * @param id
     * @return
     */
    @ApiOperation(value = "根据Id修改收货地址")
    @RequestMapping(value = "/updateAdd/{id}",method = RequestMethod.PUT )
    public Object updateAdd(@RequestBody Address address,
                              @PathVariable Integer id){
        boolean b = addressService.update
                (address,new UpdateWrapper<Address>().lambda().eq(Address::getId,id));
        if (!b){
            throw new ApiRRException(ExceptionEnums.UPDATEFAILED);
        }
        return new CommonResult().success();
    }

    /**
     * 根据地址Id查询地址信息
     */
    @ApiOperation(value = "根据地址Id查询地址信息")
    @RequestMapping(value = "/queryAddById/{id}",method = RequestMethod.GET )
    public Object queryAddInfo(@PathVariable Integer id){
        Address address = addressService.getById(id);
        return new CommonResult().success(address);
    }

    /**
     * 根据用户id分页查询地址信息
     */
    @ApiOperation(value = "根据用户id分页查询地址信息")
    @RequestMapping(value = "/queryAdd/{userId}",method = RequestMethod.GET )
    public Object queryAddByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                     @RequestParam (name = "size" ,defaultValue = "10") Integer size,
                                     @PathVariable Integer userId){
        Page<Address> addressPage = new Page<>(page,size);
        IPage<Address> data = addressService.pageByExample
                (addressPage,new QueryWrapper<Address>().lambda().eq(Address::getUserId,userId));
        return new CommonResult().success(data);
    }

    /**
     * 分页查询所有收货地址
     */
    @ApiOperation(value = "分页查询所有收货地址")
    @RequestMapping(value = "/queryAllAdd",method = RequestMethod.GET )
    public Object queryAddByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                     @RequestParam (name = "size" ,defaultValue = "10") Integer size){
        Page<Address> addressPage = new Page<>(page,size);
        IPage<Address> data = addressService.queryByPage(addressPage);
        return new CommonResult().success(data);
    }

    /**
     * 根据id删除收货地址
     */
    @ApiOperation(value = "根据id删除收货地址")
    @RequestMapping(value = "/removeAdd",method = RequestMethod.DELETE )
    public Object removeAdd(@RequestParam Integer id){
        Address address = addressService.getById(id);
        if (address == null){
            throw new ApiRRException(ExceptionEnums.NOTFOUND);
        }
        addressService.remove(new QueryWrapper<Address>().lambda().eq(Address::getId,id));
        return new CommonResult().success();
    }
}
