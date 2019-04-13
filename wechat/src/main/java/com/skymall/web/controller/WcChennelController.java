package com.skymall.web.controller;

import com.skymall.annotation.IgnoreAuth;
import com.skymall.service.IWcChannelService;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mshop/wc/channel")
public class WcChennelController {

    @Resource
    private IWcChannelService wcChannelService;

    @IgnoreAuth
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public Response query(){
        return Response.success();
    }










}
