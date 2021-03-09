package com.zjj.service;

import com.zjj.dto.SysDoctor;

import java.util.List;

public interface ISysDoctorService {

    /**
     * 根据条件分页查询医生列表
     *
     * @param doctor 医生信息
     * @return 医生信息集合信息
     */
    List<SysDoctor> getDoctorList(SysDoctor doctor, int page, int size);

    /**
     * 通过医生工号查询医生信息
     *
     * @param doctId 医生工号
     * @return 医生对象信息
     */
    public SysDoctor selectDoctorById(String doctId);


    /**
     * 校验工号是否唯一
     *
     * @param doctId 医生工号
     * @return 结果
     */
    String checkDoctIdUnique(String doctId);

    /**
     * 校验手机号码是否唯一
     *
     * @param doctor 医生信息
     * @return 结果
     */
    public String checkPhoneUnique(SysDoctor doctor);

    /**
     * 校验email是否唯一
     *
     * @param doctor 医生信息
     * @return 结果
     */
    public String checkEmailUnique(SysDoctor doctor);


    /**
     * 新增医生信息
     *
     * @param doctor 医生信息
     * @return 结果
     */
    int insertDoctor(SysDoctor doctor);

    /**
     * 修改医生信息
     *
     * @param doctor 医生信息
     * @return 结果
     */
    int updateDoctor(SysDoctor doctor);

    /**
     * 批量删除医生信息
     *
     * @param doctIds 需要删除的工号id
     * @return 结果
     */
    int deleteDoctorByIds(String[] doctIds);

    /**
     * 重置医生密码
     *
     * @param doctor 医生信息
     * @return 结果
     */
    public int resetPwd(SysDoctor doctor);

    /**
     * 修改医生状态
     *
     * @param doctor 医生信息
     * @return 结果
     */
    int updateDoctorStatus(SysDoctor doctor);

    SysDoctor selectPasswordById(String username);
}
