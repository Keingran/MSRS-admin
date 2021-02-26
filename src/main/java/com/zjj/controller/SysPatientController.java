package com.zjj.controller;


import com.zjj.common.Result;
import com.zjj.common.constant.HttpStatus;
import com.zjj.dto.SysPatient;
import com.zjj.dto.SysUser;
import com.zjj.dto.model.LoginUser;
import com.zjj.service.ISysPatientService;
import com.zjj.utils.MessageUtils;
import com.zjj.web.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 就诊人信息
 */
@RestController
@RequestMapping("/patient")
public class SysPatientController {

    private static final Logger log = LoggerFactory.getLogger(SysPatientController.class);


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysPatientService patientService;

    /**
     * 获取就诊人列表
     *
     * @param request 根据token获取用户信息
     * @return 就诊人信息
     */
    @GetMapping("/list")
    public Result getPatientList(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();


        // 如果用户状态为 "1" 则用户账号已停用
        if (user.getStatus().equals("1")) {
            log.info("[SysPatientController --> getPatientList] 用户已停用");
            return Result.error(HttpStatus.LOGIN_STATUS, MessageUtils.message("user.status.blocked"));
        }

        log.info("[SysPatientController --> getPatientList] 用户{}的状态：{}", user.getPhone(), user.getStatus());
        List<SysPatient> patientList = patientService.getPatientList(user.getUserId());
        return Result.success(patientList);
    }
}
