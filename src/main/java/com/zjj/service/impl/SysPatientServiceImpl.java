package com.zjj.service.impl;

import com.zjj.dto.SysPatient;
import com.zjj.mapper.SysPatientMapper;
import com.zjj.service.ISysPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 就诊人信息 服务层处理
 */

@Service
public class SysPatientServiceImpl implements ISysPatientService {

    @Autowired
    private SysPatientMapper patientMapper;

    /**
     * 获取就诊人信息
     *
     * @param userId 用户id
     * @return 就诊人信息
     */
    @Override
    public List<SysPatient> getPatientList(Long userId) {
        return patientMapper.getPatientList(userId);
    }
}
