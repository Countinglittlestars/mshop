package com.skymall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.dao.AddressMapper;
import com.skymall.domain.Address;
import com.skymall.service.impl.AddressServiceImpl;
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
    private AddressMapper addressMapper;
    @Resource
    private AddressServiceImpl addressServiceImpl;

    /**
     * 新增收货地址
     * @param address
     * @return
     */
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Response addAddress(@RequestBody Address address){
        addressServiceImpl.save(address);
        return Response.success(address.getId());
    }

    /**
     * 根据Id修改收货地址
     * @param address
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateAdd/{id}",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public Response updateAdd(@RequestBody Address address,
                              @PathVariable Integer id){
        UpdateWrapper<Address> addressUpdateWrapper = new UpdateWrapper<>();
        addressServiceImpl.update(address,addressUpdateWrapper.eq("id",id));
        return Response.success();
    }

    /**
     * 根据地址Id查询地址信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryAddById",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAddInfo(@RequestParam Integer id){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        List<Address> list = addressMapper.selectList(queryWrapper.eq("id",id));
        HashMap<String,Object> map = new HashMap<>();
        map.put("address",list);
        return Response.success(list);
    }

    /**
     * 根据用户id分页查询地址信息
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryAdd",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAddByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                     @RequestParam (name = "size" ,defaultValue = "10") Integer size,
                                     @RequestParam Integer userId){
        Page<Address> addressPage = new Page<>(page,size);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        IPage<Address> data = addressMapper.selectPage(addressPage,queryWrapper.eq("user_id",userId));
        return Response.success(data);
    }

    /**
     * 分页查询所有收货地址
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/queryAllAdd",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Response queryAddByUserId(@RequestParam (name = "page",defaultValue = "1") Integer page,
                                     @RequestParam (name = "size" ,defaultValue = "10") Integer size){
        Page<Address> addressPage = new Page<>(page,size);
        IPage<Address> data = addressMapper.selectPage(addressPage,null);
        return Response.success(data);
    }

    /**
     * 根据id删除收货地址
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeAdd",method = RequestMethod.DELETE,produces = "application/json;charset=UTF-8")
    public Response removeAdd(@RequestParam Integer id){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        addressServiceImpl.remove(queryWrapper.eq("id",id));
        return Response.success();
    }
}
