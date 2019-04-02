package com.skymall.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.skymall.constant.WcConstant;
import com.skymall.domain.User;
import com.skymall.service.IWcFootPrintService;
import com.skymall.utils.ApiPageUtils;
import com.skymall.utils.DateUtils;
import com.skymall.vo.Response;
import com.skymall.vo.wechat.FootprintVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "/mshop/wc/footprint")
public class WcFootPrintController {

    @Resource
    HttpServletRequest request;

    @Resource
    IWcFootPrintService footprintService;

    /**
     * 获取足迹列表
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/list")
    public Response list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        Integer userId = ((Long)request.getAttribute(WcConstant.LOGIN_USER_KEY)).intValue();
        Map<String, Object> resultObj = new HashMap<String, Object>();
        //查询列表数据
        PageHelper.startPage(0, 10, false);
        List<FootprintVo> footprintVos = footprintService.queryListFootprint(userId + "");

        ApiPageUtils pageUtil = new ApiPageUtils(footprintVos, 0, size, page);
        //
        Map<String, List<FootprintVo>> footPrintMap = new TreeMap<String, List<FootprintVo>>(new Comparator<String>() {
            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(String o1, String o2) {

                //指定排序器按照降序排列
                return o2.compareTo(o1);
            }
        });

        if (null != footprintVos && footprintVos.size() > 0) {
            for (FootprintVo footprintVo : footprintVos) {
                String addTime = DateUtils.timeToStr(footprintVo.getAddTime(), DateUtils.DATE_PATTERN);
                List<FootprintVo> tmpList = footPrintMap.get(addTime);
                if (null == footPrintMap.get(addTime)) {
                    tmpList = new ArrayList<FootprintVo>();
                }
                tmpList.add(footprintVo);
                footPrintMap.put(addTime, tmpList);
            }
            List<List<FootprintVo>> footprintVoList = new ArrayList<List<FootprintVo>>();
            for (Map.Entry<String, List<FootprintVo>> entry : footPrintMap.entrySet()) {
                footprintVoList.add(entry.getValue());
            }
            resultObj.put("count", pageUtil.getCount());
            resultObj.put("totalPages", pageUtil.getTotalPages());
            resultObj.put("numsPerPage", pageUtil.getNumsPerPage());
            resultObj.put("currentPage", pageUtil.getCurrentPage());
            resultObj.put("data", footprintVoList);
        }

        return Response.success(resultObj);
    }

}
