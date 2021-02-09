package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.dto.SysDept;
import com.zjj.dto.TreeList;
import com.zjj.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 科室信息
 */

@RestController
@RequestMapping("/dept")
public class SysDeptController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @GetMapping("/list")
    public Result list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return Result.success(depts);
    }

    /**
     * 获取部门列表（树形）
     */
    @GetMapping("/treeList")
    public Result treeList(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        //List<SysDept> deptTree = TreeListUtil.buildTree(depts);
        List<TreeList> deptTree = deptService.buildDeptTreeList(depts);
        return Result.success(deptTree);
    }

    @GetMapping("/getById/{id}")
    public SysDept getById(@PathVariable("id") String id) {
        String deptName = stringRedisTemplate.opsForValue().get("deptName_" + id);
        System.out.println("sysDept" + deptName);
        SysDept sysDept = null;
        if (deptName == null) {
            sysDept = deptService.getById(id);
            deptName = sysDept.getDeptName();
            stringRedisTemplate.opsForValue().set("deptName_" + id, deptName);
        }
        sysDept = (SysDept) redisTemplate.opsForValue().get("dept_" + id);
        System.out.println("sysDept" + sysDept);
        if (sysDept == null) {
            sysDept = deptService.getById(id);
            redisTemplate.opsForValue().set("dept_" + id, sysDept);
        }
        return sysDept;
    }

}