package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.service.impl.AddressServiceImpl;
import com.skymall.vo.CommonResult;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author linchusen
 * @date 2019/3/14
 * 收货地址管理
 */

@RestController
public class AddressController {

    @Resource
    private AddressServiceImpl addressService;

    /**
     * 新增收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST )
    public Object addAddress(@RequestBody Address address){
        addressService.save(address);
        return new CommonResult().success(address.getId());
    }

    /**
     * 根据Id修改收货地址
     * @param address
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAdd/{id}",method = RequestMethod.PUT )
    public Object updateAdd(@RequestBody Address address,
                              @PathVariable Integer id){
        UpdateWrapper<Address> addressUpdateWrapper = new UpdateWrapper<>();
        addressService.update(address,addressUpdateWrapper.eq("id",id));
        return new CommonResult().success();
    }

    /**
     * 根据地址Id查询地址信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryAddById",method = RequestMethod.GET )
    public Object queryAddInfo(@RequestParam Integer id){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        List<Address> list = addressService.list(queryWrapper.eq("id",id));
        return new CommonResult().success(list);
    }

    /**
     * 根据用户id分页查询地址信息
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryAdd",method = RequestMethod.GET )
    public Object queryAddByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                     @RequestParam (name = "size" ,defaultValue = "10") Integer size,
                                     @RequestParam Integer userId){
        Page<Address> addressPage = new Page<>(page,size);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        IPage<Address> data = addressService.pageByCondition(addressPage,queryWrapper.eq("user_id",userId));
        return new CommonResult().success(data);
    }

    /**
     * 分页查询所有收货地址
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryAllAdd",method = RequestMethod.GET )
    public Response queryAddByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                     @RequestParam (name = "size" ,defaultValue = "10") Integer size){
        Page<Address> addressPage = new Page<>(page,size);
        IPage<Address> data = addressService.queryByPage(addressPage);
        return Response.success(data);
    }

    /**
     * 根据id删除收货地址
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeAdd",method = RequestMethod.DELETE )
    public Response removeAdd(@RequestParam Integer id){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        addressService.remove(queryWrapper.eq("id",id));
        return Response.success();
    }
}
