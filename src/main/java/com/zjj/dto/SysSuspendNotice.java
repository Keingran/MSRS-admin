package com.zjj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 停诊公告表 sys_suspend_notice
 */
public class SysSuspendNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停诊公告ID
     */
    private Long noticeId;

    /**
     * 停诊公告标题
     */
    private String noticeTitle;

    /**
     * 停诊科室ID
     */
    private Long deptId;

    /**
     * 停诊科室名称
     */
    private String deptName;

    /**
     * 停诊日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date visitDate;

    /**
     * 停诊时间段 0上午 1下午
     */
    private String visitPeriod;

    /**
     * 停诊医生特长
     */
    private String doctorSpecial;

    /**
     * 停诊医生职称
     */
    private String doctorType;

    /**
     * 平台公告状态 0正常 1关闭
     */
    private String status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitPeriod() {
        return visitPeriod;
    }

    public void setVisitPeriod(String visitPeriod) {
        this.visitPeriod = visitPeriod;
    }

    public String getDoctorSpecial() {
        return doctorSpecial;
    }

    public void setDoctorSpecial(String doctorSpecial) {
        this.doctorSpecial = doctorSpecial;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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
                .append("noticeId", getNoticeId())
                .append("noticeTitle", getNoticeTitle())
                .append("deptId", getDeptId())
                .append("deptName", getDeptName())
                .append("visitDate", getVisitDate())
                .append("visitPeriod", getVisitPeriod())
                .append("doctorSpecial", getDoctorSpecial())
                .append("doctorType", getDoctorType())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
