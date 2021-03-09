package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.dto.SysDept;
import com.zjj.dto.TreeList;
import com.zjj.service.ISysDeptService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private static final String NOT_UNIQUE = "1"; // 校验返回结果码

    /**
     * 获取部门列表
     */
    @GetMapping("/list")
    public Result list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return Result.success(depts);
    }

    /**
     * 查询所有部门列表
     */
    @GetMapping("/listAll")
    public Result selectDeptAll(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptAll(dept);
        return Result.success(depts);
    }

    /**
     * 根据id查询所有部门信息
     */
    @GetMapping("/listAll/{deptId}")
    public Result selectDeptAllById(@PathVariable Long deptId) {
        SysDept dept = deptService.selectDeptAllById(deptId);
        return Result.success(dept);
    }

    /**
     * 新增部门
     */
    @PostMapping("/add")
    public Result addDept(@Validated @RequestBody SysDept dept) {
        if (NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return Result.error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        return PageUtils.toResult(deptService.insertDept(dept), "dept.add.success");
    }

    /**
     * 修改部门
     */
    @PutMapping("/update")
    public Result updateDept(@Validated @RequestBody SysDept dept) {
        if (NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
            return Result.error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        } else if (dept.getParentId().equals(dept.getDeptId())) {
            return Result.error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        return PageUtils.toResult(deptService.updateDept(dept), "dept.update.success");
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/delete/{deptId}")
    public Result deleteDeptById(@PathVariable Long deptId) {
        if (deptService.hasChildByDeptId(deptId)) {
            return Result.error("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return Result.error("部门存在用户,不允许删除");
        }
        return PageUtils.toResult(deptService.deleteDeptById(deptId), "dept.delete.success");
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

    @GetMapping("/getById")
    public Result getById(@RequestParam("deptId") String deptId) {
        String deptName = stringRedisTemplate.opsForValue().get("deptName_" + deptId);
        SysDept sysDept = null;
        if (deptName == null) {
            sysDept = deptService.getById(deptId);
            deptName = sysDept.getDeptName();
            stringRedisTemplate.opsForValue().set("deptName_" + deptId, deptName);
        }
        sysDept = (SysDept) redisTemplate.opsForValue().get("dept_" + deptId);
        if (sysDept == null) {
            sysDept = deptService.getById(deptId);
            redisTemplate.opsForValue().set("dept_" + deptId, sysDept);
        }
        return Result.success(sysDept);
    }

}
