package com.zjj.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.dto.SysDoctor;
import com.zjj.service.ISysDoctorService;
import com.zjj.service.ISysPostService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.PageUtils;
import com.zjj.utils.SecurityUtils;
import com.zjj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生信息
 */
@RestController
@RequestMapping("/doct")
public class SysDoctorController {

    @Autowired
    private ISysDoctorService doctorService;

    @Autowired
    private ISysPostService postService;

    private static final String NOT_UNIQUE = "1"; // 校验返回结果码

    /**
     * 获取用户列表
     */
    @PostMapping("/list")
    public Result getDoctorList(@RequestBody SysDoctor doctor) {
        int page = StringUtils.isNotNull(doctor.getPage()) ? doctor.getPage() : 1;
        int size = StringUtils.isNotNull(doctor.getSize()) ? doctor.getSize() : 10;

        List<SysDoctor> list = doctorService.getDoctorList(doctor, page, size);

        // 分页处理
        JSONObject pageResult = PageUtils.getPageResult(list, page, size);
        return Result.success(pageResult);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = {"/detail/", "/detail/{doctId}"})
    public Result getDoctorDetail(@PathVariable(value = "doctId", required = false) String doctId) {
        Result result = Result.success();
        result.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(doctId)) {
            result.put(Result.DATA_TAG, doctorService.selectDoctorById(doctId));
            result.put("postIds", postService.selectPostListByDoctId(doctId));
        }
        return result;
    }


    /**
     * 新增医生
     */
    @PostMapping("/add")
    public Result addDoctor(@Validated @RequestBody SysDoctor doctor) {
        if (NOT_UNIQUE.equals(doctorService.checkDoctIdUnique(doctor.getDoctId()))) {
            return Result.error("新增医生'" + doctor.getName() + "'失败，医生工号已存在");
        } else if (StringUtils.isNotEmpty(doctor.getPhone())
                && NOT_UNIQUE.equals(doctorService.checkPhoneUnique(doctor))) {
            return Result.error("新增医生'" + doctor.getName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(doctor.getEmail())
                && NOT_UNIQUE.equals(doctorService.checkEmailUnique(doctor))) {
            return Result.error("新增医生'" + doctor.getName() + "'失败，邮箱账号已存在");
        }
        // 加密处理
        //doctor.setPassword(SecurityUtils.encryptPassword(doctor.getPassword()));
        return PageUtils.toResult(doctorService.insertDoctor(doctor), "doctor.add.success");
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result updateDoctor(@Validated @RequestBody SysDoctor doctor) {
        if (StringUtils.isNotEmpty(doctor.getPhone())
                && NOT_UNIQUE.equals(doctorService.checkPhoneUnique(doctor))) {
            return Result.error("修改用户'" + doctor.getName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(doctor.getEmail())
                && NOT_UNIQUE.equals(doctorService.checkEmailUnique(doctor))) {
            return Result.error("修改用户'" + doctor.getName() + "'失败，邮箱账号已存在");
        }
        return PageUtils.toResult(doctorService.updateDoctor(doctor), "doctor.update.success");
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete/{doctIds}")
    public Result deleteDoctor(@PathVariable String[] doctIds) {
        return PageUtils.toResult(doctorService.deleteDoctorByIds(doctIds), "doctor.delete.success");
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPwd")
    public Result resetDoctorPwd(@RequestBody SysDoctor doctor) {
        doctor.setPassword(SecurityUtils.encryptPassword(doctor.getPassword()));
        return PageUtils.toResult(doctorService.resetPwd(doctor), "doctor.reset.pwd.success");
    }

    /**
     * 状态修改
     */
    @PostMapping("/changeStatus")
    public Result changeDoctorStatus(@RequestBody SysDoctor doctor) {
        return PageUtils.toResult(doctorService.updateDoctorStatus(doctor), "doctor.change.status.success");
    }

}
