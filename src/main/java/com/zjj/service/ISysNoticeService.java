package com.zjj.service;

import com.zjj.dto.SysPlatformNotice;
import com.zjj.dto.SysSuspendNotice;

import java.util.List;
import java.util.Map;

/**
 * 系统公告 服务层
 */
public interface ISysNoticeService {

    /**
     * 查询平台公告列表
     *
     * @param reqParams   平台公告信息
     * @param currentPage 分页编码
     * @param pageSize    分页大小
     * @return 平台公告集合
     */
    List<SysPlatformNotice> getPlatformList(Map<String, String> reqParams, int currentPage, int pageSize);

    /**
     * 根据平台公告ID查询信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysPlatformNotice getPlatformById(Long noticeId);


    /**
     * 查询停诊公告列表
     *
     * @param reqParams   停诊公告信息
     * @param currentPage 分页编码
     * @param pageSize    分页大小
     * @return 停诊公告集合
     */
    List<SysSuspendNotice> getSuspendList(Map<String, String> reqParams, int currentPage, int pageSize);

    /**
     * 根据停诊公告ID查询信息
     *
     * @param noticeId 公告ID
     * @return 停诊信息
     */
    SysSuspendNotice getSuspendById(Long noticeId);
}
