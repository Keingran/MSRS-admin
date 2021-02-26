package com.zjj.service;

import com.zjj.dto.SysPatient;

import java.util.List;

public interface ISysPatientService {
    /**
     * 获取就诊人信息
     *
     * @param userId 用户id
     * @return 就诊人信息
     */
    List<SysPatient> getPatientList(Long userId);
}
