package com.zjj.mapper;

import com.zjj.dto.SysDept;

import java.util.List;

/**
 * 部门信息 数据层
 */
public interface SysDeptMapper {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    SysDept getById(String id);
}
