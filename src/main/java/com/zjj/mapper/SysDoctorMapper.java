package com.zjj.mapper;

import com.zjj.dto.SysDoctor;

import java.util.List;
import java.util.Map;

public interface SysDoctorMapper {
    /**
     * 根据条件分页查询医生列表
     *
     * @param doctor 医生信息
     * @return 医生信息集合信息
     */
    List<SysDoctor> getDoctorList(SysDoctor doctor);

    /**
     * 通过医生工号查询医生信息
     *
     * @param doctId 医生工号
     * @return 医生对象信息
     */
    SysDoctor selectDoctorById(String doctId);

    /**
     * 校验工号是否唯一
     *
     * @param doctId 医生工号
     * @return 结果
     */
    int checkDoctIdUnique(String doctId);

    /**
     * 校验手机号码是否唯一
     *
     * @param phone 手机号码
     * @return 结果
     */
    SysDoctor checkPhoneUnique(String phone);

    /**
     * 校验email是否唯一
     *
     * @param email 医生信息
     * @return 结果
     */
    SysDoctor checkEmailUnique(String email);


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

    SysDoctor selectPasswordById(String doctId);
}
