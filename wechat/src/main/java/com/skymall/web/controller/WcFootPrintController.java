package com.skymall.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skymall.constant.WcConstant;
import com.skymall.domain.User;
import com.skymall.service.IWcFootPrintService;
import com.skymall.vo.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/mshop/wc/footPrint")
public class WcFootPrintController {

    @Resource
    HttpServletRequest request;

    @Resource
    IWcFootPrintService footPrintService;

    /**
     * 获取足迹列表
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/list")
    public Response list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        Integer userId = Integer.valueOf(request.getParameter(WcConstant.LOGIN_USER_KEY));
        Page page1 = new Page(page, size);
        //获取对应的商品信息加上足迹信息
        IPage page2 = footPrintService.queryByPage(page1, userId);
        return Response.success(page2);
    }

}
