package com.zjj.service;

import com.zjj.dto.SysPatient;
import com.zjj.dto.SysPatientCard;

import java.util.List;

public interface ISysPatientService {

    /**
     * 获取就诊人信息
     *
     * @param userId 用户id
     * @return 就诊人信息
     */
    List<SysPatient> getPatientList(Long userId);

    /**
     * 检查就诊人是否存在
     *
     * @param idCardNo   证件号码
     * @param idCardType 证件类型
     * @param userId     用户id
     * @return 存在 or null
     */
    SysPatient bindCheck(String idCardNo, String idCardType, Long userId);

    /**
     * 添加就诊人信息
     *
     * @param patient 就诊人信息
     */
    void insertPatient(SysPatient patient);

    /**
     * 查询就诊卡列表
     *
     * @param patientId 就诊人id
     * @return 就诊卡列表
     */
    List<SysPatientCard> getPatientCardList(Long patientId);

    /**
     * 解绑就诊卡
     *
     * @param patientCard 就诊卡信息
     */
    void unbindPatientCard(SysPatientCard patientCard);

    /**
     * 绑定就诊卡
     *
     * @param patientCard 就诊卡信息
     */
    void bindPatientCard(SysPatientCard patientCard);


    /**
     * 获取就诊人详情
     *
     * @param idCardNo   证件号码
     * @param idCardType 证件类型
     * @param userId     用户id
     * @return 存在 or null
     */
    SysPatient getPatientDetail(String idCardNo, String idCardType, Long userId);

    /**
     * 删除就诊人
     *
     * @param patient 就诊人信息
     */
    void unBindPatient(SysPatient patient);

    SysPatient getPatientData(Long patientId);

    void changePatient(SysPatient patient);
}
