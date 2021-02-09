package com.zjj.mapper;

import com.zjj.dto.SysPlatformNotice;
import com.zjj.dto.SysSuspendNotice;

import java.util.List;
import java.util.Map;

public interface SysNoticeMapper {
    /**
     * 查询平台公告列表
     *
     * @param reqParams 平台公告信息
     * @return 平台公告集合
     */
    List<SysPlatformNotice> selectPlatformList(Map<String, String> reqParams);

    /**
     * 根据平台公告ID查询信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysPlatformNotice selectPlatformById(Long noticeId);

    /**
     * 查询停诊公告列表
     *
     * @param reqParams 停诊公告信息
     * @return 停诊公告集合
     */
    List<SysSuspendNotice> selectSuspendList(Map<String, String> reqParams);

    /**
     * 根据停诊公告ID查询信息
     *
     * @param noticeId 公告ID
     * @return 停诊信息
     */
    SysSuspendNotice selectSuspendById(Long noticeId);
}
