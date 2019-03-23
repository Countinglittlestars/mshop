package com.skymall.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skymall.annotation.LoginUser;
import com.skymall.constant.WcConstant;
import com.skymall.dao.CollectMapper;
import com.skymall.domain.Collect;
import com.skymall.domain.User;
import com.skymall.exception.ApiRRException;
import com.skymall.service.IWcCollectService;
import com.skymall.utils.BeanUtils;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.CollectVo;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/mshop/wc/collect")
public class WcCollectController extends AbstractController{

    @Resource
    HttpServletRequest request;

    @Resource
    IWcCollectService wcCollectService;


    /**
     * 根据用户id，返回所有的收藏信息
     * @param
     * @return
     */
    @RequestMapping(value = "/queryListByUserId", method = RequestMethod.GET)
    public Response queryListByUserId(Integer typeId){
        Integer userId = Integer.valueOf(request.getParameter(WcConstant.LOGIN_USER_KEY));
        if(userId == null) throw new ApiRRException("用户不存在", 500);
        Map map = new HashMap<String, Integer>();
        map.put("userId", userId);
        List<CollectVo> collectVoList = wcCollectService.queryListByUserId(map);
        return Response.success(collectVoList);
    }


    /**
     * 取消或添加收藏
     * @return
     */
    @PostMapping("/addordelete")
    public Object addordelete() {
        User loginUser = new User();
        JSONObject jsonParam = getJsonRequest();
        Integer typeId = jsonParam.getInteger("typeId");
        Integer valueId = jsonParam.getInteger("valueId");

        Map param = new HashMap();
        param.put("userId", loginUser.getId());
        param.put("typeId", typeId);
        param.put("valueId", valueId);
        List<CollectVo> collectEntities = wcCollectService.queryListByUserId(param);
        //
        Boolean collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
            CollectVo collectEntity = new CollectVo();
            collectEntity.setAddTime(System.currentTimeMillis() / 1000);
            collectEntity.setTypeId(typeId);
            collectEntity.setValueId(valueId);
            collectEntity.setIsAttention(0);
            collectEntity.setUserId(loginUser.getId());
            //添加收藏
            Collect collect = new Collect();
            BeanUtils.mapping(collectEntity, collect);
            collectRes = wcCollectService.save(collect);
        } else {
            //取消收藏
            collectRes = wcCollectService.removeById(collectEntities.get(0).getId());
            handleType = "delete";
        }

        if (collectRes == true) {
            Map data = new HashMap();
            data.put("type", handleType);
            return Response.success(data);
        }
        return Response.error("操作失败");
    }

}
