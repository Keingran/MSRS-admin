package com.zjj.service.impl;

import com.zjj.dto.SysMenu;
import com.zjj.mapper.SysMenuMapper;
import com.zjj.service.ISysHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 系统信息 服务层处理
 */
@Service
public class SysHospitalServiceImpl implements ISysHospitalService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 查询系统菜单
     *
     * @return 系统菜单
     */
    @Override
    public List<SysMenu> selectMenuList() {
        return menuMapper.selectMenuList();
    }
}
