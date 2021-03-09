package com.zjj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjj.dto.SysDoctor;
import com.zjj.dto.SysPosition;
import com.zjj.mapper.SysDoctorMapper;
import com.zjj.service.ISysDoctorService;
import com.zjj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysDoctorServiceImpl implements ISysDoctorService {

    @Autowired
    private SysDoctorMapper doctorMapper;

    /**
     * 根据条件分页查询医生列表
     *
     * @param doctor 医生信息
     * @return 医生信息集合信息
     */
    @Override
    public List<SysDoctor> getDoctorList(SysDoctor doctor, int page, int size) {
        PageHelper.startPage(page, size);
        return doctorMapper.getDoctorList(doctor);
    }

    /**
     * 通过医生工号查询医生信息
     *
     * @param doctId 医生工号
     * @return 医生对象信息
     */
    @Override
    public SysDoctor selectDoctorById(String doctId) {
        return doctorMapper.selectDoctorById(doctId);
    }

    /**
     * 校验工号是否唯一
     *
     * @param doctId 医生工号
     * @return 结果
     */
    @Override
    public String checkDoctIdUnique(String doctId) {
        int count = doctorMapper.checkDoctIdUnique(doctId);
        if (count > 0) {
            return "1";
        }
        return "0";
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param doctor 医生信息
     * @return 结果
     */
    @Override
    public String checkPhoneUnique(SysDoctor doctor) {
        String doctId = StringUtils.isNull(doctor.getDoctId()) ? "-1" : doctor.getDoctId();
        SysDoctor info = doctorMapper.checkPhoneUnique(doctor.getPhone());
        if (StringUtils.isNotNull(info) && !info.getDoctId().equals(doctId)) {
            return "1";
        }
        return "0";
    }

    /**
     * 校验email是否唯一
     *
     * @param doctor 医生信息
     * @return 结果
     */
    @Override
    public String checkEmailUnique(SysDoctor doctor) {
        String doctId = StringUtils.isNull(doctor.getDoctId()) ? "-1" : doctor.getDoctId();
        SysDoctor info = doctorMapper.checkEmailUnique(doctor.getEmail());
        if (StringUtils.isNotNull(info) && !info.getDoctId().equals(doctId)) {
            return "1";
        }
        return "0";
    }

    /**
     * 新增医生信息
     *
     * @param doctor 医生信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertDoctor(SysDoctor doctor) {
        return doctorMapper.insertDoctor(doctor);
    }

    /**
     * 修改医生信息
     *
     * @param doctor 医生信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateDoctor(SysDoctor doctor) {
        return doctorMapper.updateDoctor(doctor);
    }

    /**
     * 批量删除医生信息
     *
     * @param doctIds 需要删除的工号id
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteDoctorByIds(String[] doctIds) {
        return doctorMapper.deleteDoctorByIds(doctIds);
    }

    /**
     * 重置医生密码
     *
     * @param doctor 医生信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int resetPwd(SysDoctor doctor) {
        return doctorMapper.updateDoctor(doctor);
    }

    /**
     * 修改医生状态
     *
     * @param doctor 医生信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateDoctorStatus(SysDoctor doctor) {
        return doctorMapper.updateDoctor(doctor);

    }

    @Override
    public SysDoctor selectPasswordById(String username) {
        return doctorMapper.selectPasswordById(username);
    }
}
