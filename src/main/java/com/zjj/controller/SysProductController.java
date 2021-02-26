package com.zjj.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.dto.SysProductBatch;
import com.zjj.dto.SysProductInfo;
import com.zjj.service.ISysProductService;
import com.zjj.utils.DateUtils;
import com.zjj.utils.PageUtils;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 号源信息
 */
@RestController
@RequestMapping("/product")
public class SysProductController {
    private static final Logger log = LoggerFactory.getLogger(SysProductController.class);

    @Autowired
    private ISysProductService productService;

    // 可预约周数
    private static final int WEEK = 3;

    /**
     * 获取医院部门的号源信息
     *
     * @param dto deptCode部门 week查询周数
     * @return 号源列表
     */
    @PostMapping("/list")
    public Result getProductList(@RequestBody JSONObject dto) {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });

        int page = StringUtils.isNotEmpty(param.get("week")) ? Integer.parseInt(param.get("week")) : 1;
        String deptId = param.get("deptId");
        Date nowDate = DateUtils.getNowDate();
        String beginTime = DateUtils.getDateAfter(nowDate, 1);
        String endTime = DateUtils.getDateAfter(nowDate, WEEK * 7);

        log.info("[SysProductController --> getProductList] 查询参数：{}", param);
        List<SysProductBatch> productList = productService.getProductList(deptId, beginTime, endTime, page);

        // 分页处理
        JSONObject pageResult = PageUtils.getPageResult(productList, page, 7);
        return Result.success(pageResult);
    }


    /**
     * 获取部门一天的号源信息
     *
     * @param dto deptId部门 batchDate日期
     * @return 一天的号源信息
     */
    @PostMapping("/detail")
    public Result getProductDetail(@RequestBody JSONObject dto) {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        String deptId = param.get("deptId");
        String batchDate = param.get("batchDate");
        String amStatus = "0";
        String pmStatus = "1";

        log.info("[SysProductController --> getProductDetail] 查询参数：{}", param);
        List<SysProductInfo> amProductDetail = productService.getProductDetail(deptId, batchDate, amStatus);
        List<SysProductInfo> pmProductDetail = productService.getProductDetail(deptId, batchDate, pmStatus);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MORNING", amProductDetail);
        jsonObject.put("AFTERNOON", pmProductDetail);
        return Result.success(jsonObject);
    }

    /**
     * 确认号源信息
     *
     * @param dto deptId部门 batchDate日期 uniqProductKey号源编号
     * @return 号源信息
     */
    @PostMapping("/confirm")
    public Result productConfirm(@RequestBody JSONObject dto) {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        String deptId = param.get("deptId");
        String batchDate = param.get("batchDate");
        String uniqProductKey = param.get("uniqProductKey");
        SysProductInfo productInfo = productService.productConfirm(deptId, batchDate, uniqProductKey);
        return Result.success(productInfo);
    }
}
