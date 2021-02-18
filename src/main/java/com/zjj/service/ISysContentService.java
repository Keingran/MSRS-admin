package com.zjj.service;

import com.zjj.dto.SysContent;

/**
 * 系统文章信息 服务层
 */
public interface ISysContentService {

    /**
     * 查询文章
     *
     * @param content 类型 + 标签
     * @return 文章信息
     */
    SysContent selectContent(SysContent content);
}
