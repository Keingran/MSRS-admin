package com.zjj.mapper;


import com.zjj.dto.SysContent;

/**
 * 文章信息 数据层
 */
public interface SysContentMapper {
    /**
     * 查询文章
     *
     * @param content 类型 + 标签
     * @return 文章信息
     */
    SysContent selectContent(SysContent content);
}
