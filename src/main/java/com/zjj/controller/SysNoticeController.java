package com.zjj.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.dto.SysPlatformNotice;
import com.zjj.dto.SysSuspendNotice;
import com.zjj.service.ISysNoticeService;
import com.zjj.utils.PageUtils;
import com.zjj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 公告信息
 */

@RestController
@RequestMapping("/notice")
public class SysNoticeController {
    private static final Logger log = LoggerFactory.getLogger(SysNoticeController.class);

    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取平台公告列表
     */
    @PostMapping("/platFormList")
    public Result getPlatformList(@RequestBody JSONObject dto) {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        int page = StringUtils.isNotEmpty(param.get("page")) ? Integer.parseInt(param.get("page")) : 1;
        int size = StringUtils.isNotEmpty(param.get("size")) ? Integer.parseInt(param.get("size")) : 10;

        log.info("[SysNoticeController --> getPlatformList] 查询参数：{}", param);

        List<SysPlatformNotice> list = noticeService.getPlatformList(param, page, size);
        // 分页处理
        JSONObject pageResult = PageUtils.getPageResult(list, page, size);
        return Result.success(pageResult);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @GetMapping(value = "/platFormList/{noticeId}")
    public Result getPlatformById(@PathVariable Long noticeId) {
        log.info("[SysNoticeController --> getPlatformById] 查询参数：{}", noticeId);
        return Result.success(noticeService.getPlatformById(noticeId));
    }

    /**
     * 获取停诊公告列表
     */
    @PostMapping("/suspendList")
    public Result getSuspendList(@RequestBody JSONObject dto) {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        int page = StringUtils.isNotEmpty(param.get("page")) ? Integer.parseInt(param.get("page")) : 1;
        int size = StringUtils.isNotEmpty(param.get("size")) ? Integer.parseInt(param.get("size")) : 10;

        log.info("[SysNoticeController --> getSuspendList] 查询参数：{}", param);

        List<SysSuspendNotice> list = noticeService.getSuspendList(param, page, size);
        // 分页处理
        JSONObject pageResult = PageUtils.getPageResult(list, page, size);
        return Result.success(pageResult);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @GetMapping("/suspendList/{noticeId}")
    public Result getSuspendById(@PathVariable Long noticeId) {
        log.info("[SysNoticeController --> getSuspendById] 查询参数：{}", noticeId);
        return Result.success(noticeService.getSuspendById(noticeId));
    }
}
