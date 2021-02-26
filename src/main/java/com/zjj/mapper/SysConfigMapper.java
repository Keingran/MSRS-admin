package com.zjj.mapper;

import com.zjj.dto.SysMenu;

import java.util.List;

/**
 * 系统信息 数据层
 */
public interface SysConfigMapper {
    /**
     * 查询系统菜单
     *
     * @return 系统菜单
     */
    List<SysMenu> selectMenuList();
}
