package com.zjj.service;

import com.zjj.dto.SysMenu;

import java.util.List;

/**
 * 系统信息 服务层
 */
public interface ISysHospitalService {
    /**
     * 查询系统菜单
     *
     * @return 系统菜单
     */
    List<SysMenu> selectMenuList();
}
