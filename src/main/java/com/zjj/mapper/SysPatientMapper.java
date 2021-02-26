package com.zjj.mapper;

import com.zjj.dto.SysPatient;

import java.util.List;

/**
 * 就诊人信息 数据层
 */
public interface SysPatientMapper {
    List<SysPatient> getPatientList(Long userId);
}
