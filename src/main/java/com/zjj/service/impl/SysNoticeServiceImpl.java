package com.zjj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjj.dto.SysPlatformNotice;
import com.zjj.dto.SysSuspendNotice;
import com.zjj.mapper.SysNoticeMapper;
import com.zjj.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysNoticeServiceImpl implements ISysNoticeService {

    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询平台公告列表
     *
     * @param reqParams   平台公告信息
     * @param currentPage 分页编码
     * @param pageSize    分页大小
     * @return 平台公告集合
     */
    @Override
    public List<SysPlatformNotice> getPlatformList(Map<String, String> reqParams, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return noticeMapper.selectPlatformList(reqParams);
    }

    /**
     * 根据平台公告ID查询信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysPlatformNotice getPlatformById(Long noticeId) {
        return noticeMapper.selectPlatformById(noticeId);
    }

    /**
     * 查询停诊公告列表
     *
     * @param reqParams   停诊公告信息
     * @param currentPage 分页编码
     * @param pageSize    分页大小
     * @return 停诊公告集合
     */
    @Override
    public List<SysSuspendNotice> getSuspendList(Map<String, String> reqParams, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return noticeMapper.selectSuspendList(reqParams);
    }

    /**
     * 根据平台公告ID查询信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysSuspendNotice getSuspendById(Long noticeId) {
        return noticeMapper.selectSuspendById(noticeId);
    }

}
