package com.zjj.service.impl;

import com.zjj.dto.SysPatient;
import com.zjj.dto.SysPatientCard;
import com.zjj.mapper.SysPatientMapper;
import com.zjj.service.ISysPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 检查就诊人是否存在
     *
     * @param idCardNo   证件号码
     * @param idCardType 证件类型
     * @param userId     用户id
     * @return 存在 or null
     */
    @Override
    public SysPatient bindCheck(String idCardNo, String idCardType, Long userId) {
        return patientMapper.bindCheck(idCardNo, idCardType, userId);
    }

    /**
     * 添加就诊人信息
     *
     * @param patient 就诊人信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertPatient(SysPatient patient) {
        patientMapper.insertPatient(patient);
    }

    /**
     * 查询就诊卡列表
     *
     * @param patientId 就诊人id
     * @return 就诊卡列表
     */
    @Override
    public List<SysPatientCard> getPatientCardList(Long patientId) {
        return patientMapper.getPatientCardList(patientId);
    }


    /**
     * 解绑就诊卡
     *
     * @param patientCard 就诊卡信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unbindPatientCard(SysPatientCard patientCard) {
        patientMapper.unbindPatientCard(patientCard);
    }

    /**
     * 绑定就诊卡
     *
     * @param patientCard 就诊卡信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindPatientCard(SysPatientCard patientCard) {
        patientMapper.bindPatientCard(patientCard);
    }

    /**
     * 获取就诊人详情
     *
     * @param idCardNo   证件号码
     * @param idCardType 证件类型
     * @param userId     用户id
     * @return 存在 or null
     */
    @Override
    public SysPatient getPatientDetail(String idCardNo, String idCardType, Long userId) {
        return patientMapper.getPatientDetail(idCardNo, idCardType, userId);
    }

    /**
     * 删除就诊人
     *
     * @param patient 就诊人信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unBindPatient(SysPatient patient) {
        patientMapper.unBindPatient(patient);
    }

    @Override
    public SysPatient getPatientData(Long patientId) {
        return patientMapper.getPatientData(patientId);
    }

    @Override
    public void changePatient(SysPatient patient) {
        patientMapper.changePatient(patient);
    }
}
