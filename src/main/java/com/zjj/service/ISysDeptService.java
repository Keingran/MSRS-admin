package com.zjj.service;

import com.zjj.dto.SysDept;
import com.zjj.dto.TreeList;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 部门管理 服务层
 */
public interface ISysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeList> buildDeptTreeList(List<SysDept> depts);

    SysDept getById(String id);
}
