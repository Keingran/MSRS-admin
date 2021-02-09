package com.zjj.utils;

import com.zjj.dto.SysDept;

import java.util.ArrayList;
import java.util.List;

public class TreeListUtils {

    /**
     * 将列表封装成树状结构
     */
    public static List<SysDept> buildTree(List<SysDept> deptList) {
        // 父级列表
        List<SysDept> rootDeptList = new ArrayList<>();
        for (SysDept sysDept : deptList) {
            if (sysDept.getParentId().longValue() == 0) {
                rootDeptList.add(sysDept);
            }
        }
        for (SysDept sysDept : rootDeptList) {
            childrenList(deptList, sysDept);
        }
        return rootDeptList;
    }

    /**
     * 判断父列表的id是否等于所有中的其中一个列表的parentId,相等则是子列表
     *
     * @param deptList //所有菜单
     * @param sysDept  //父菜单
     */
    public static SysDept childrenList(List<SysDept> deptList, SysDept sysDept) {
        // 子集列表
        List<SysDept> children = new ArrayList<>();
        for (SysDept dept : deptList) {
            if (dept.getParentId().equals(sysDept.getDeptId())) {
                children.add(childrenList(deptList, dept));//递归查找子菜单
            }
        }
        sysDept.setChildren(children);
        return sysDept;
    }
}
