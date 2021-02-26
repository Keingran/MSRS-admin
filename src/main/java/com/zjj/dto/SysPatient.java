package com.zjj.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 就诊人表 sys_patient
 */
public class SysPatient {

    private static final long serialVersionUID = 1L;

    /**
     * 就诊人编号
     */
    private Long patientId;

    /**
     * 就诊人名称
     */
    private String patientName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 证件号码
     */
    private String idCardNo;

    /**
     * 证件类型
     */
    private String idCardType;

    /**
     * 证件名称
     */
    private String idCardTypeView;

    /**
     * 性别 0男 1女 2未知
     */
    private String sex;

    /**
     * 出生日期
     */
    private String birthDate;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 状态 0可用 1已删除
     */
    private String isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardTypeView() {
        return idCardTypeView;
    }

    public void setIdCardTypeView(String idCardTypeView) {
        this.idCardTypeView = idCardTypeView;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("patientId", getPatientId())
                .append("patientName", getPatientName())
                .append("phone", getPhone())
                .append("idCardNo", getIdCardNo())
                .append("idCardType", getIdCardType())
                .append("idCardTypeView", getIdCardTypeView())
                .append("sex", getSex())
                .append("birthDate", getBirthDate())
                .append("address", getAddress())
                .append("userId", getUserId())
                .append("isDelete", getIsDelete())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
