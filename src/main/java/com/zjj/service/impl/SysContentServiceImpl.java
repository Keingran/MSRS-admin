package com.zjj.service.impl;

import com.zjj.dto.SysContent;
import com.zjj.mapper.SysContentMapper;
import com.zjj.service.ISysContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统文章信息 服务层处理
 */
@Service
public class SysContentServiceImpl implements ISysContentService {

    @Autowired
    private SysContentMapper contentMapper;

    /**
     * 查询文章
     *
     * @param content 类型 + 标签
     * @return 文章信息
     */
    @Override
    public SysContent selectContent(SysContent content) {
        return contentMapper.selectContent(content);
    }
}
