package com.zjj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 号源信息表 sys_product_info
 */
public class SysProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 号源id
     */
    private String uniqProductKey;

    /**
     * 批次id
     */
    private Long batchId;

    /**
     * 号源日期
     */
    private String batchDate;

    /**
     * 周描述
     */
    private String weekDesc;

    /**
     * 部门名称A
     */
    private String deptName;

    /**
     * 号源标题
     */
    private String title;

    /**
     * 医生工号
     */
    private String doctId;

    /**
     * 医生名称A
     */
    private String doctName;

    /**
     * 医生职称A
     */
    private String doctType;

    /**
     * 医生简介 / 特长A
     */
    private String doctSkill;

    /**
     * 号源费用
     */
    private String fCode;

    /**
     * 号源数量
     */
    private String nCode;

    /**
     * 时间段 0上午 1下午
     */
    private String status;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public String getUniqProductKey() {
        return uniqProductKey;
    }

    public void setUniqProductKey(String uniqProductKey) {
        this.uniqProductKey = uniqProductKey;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
        this.batchDate = batchDate;
    }

    public String getWeekDesc() {
        return weekDesc;
    }

    public void setWeekDesc(String weekDesc) {
        this.weekDesc = weekDesc;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoctId() {
        return doctId;
    }

    public void setDoctId(String doctId) {
        this.doctId = doctId;
    }

    public String getDoctName() {
        return doctName;
    }

    public void setDoctName(String doctName) {
        this.doctName = doctName;
    }

    public String getDoctType() {
        return doctType;
    }

    public void setDoctType(String doctType) {
        this.doctType = doctType;
    }

    public String getDoctSkill() {
        return doctSkill;
    }

    public void setDoctSkill(String doctSkill) {
        this.doctSkill = doctSkill;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public String getnCode() {
        return nCode;
    }

    public void setnCode(String nCode) {
        this.nCode = nCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                .append("uniqProductKey", getUniqProductKey())
                .append("batchId", getBatchId())
                .append("batchDate", getBatchDate())
                .append("weekDesc", getWeekDesc())
                .append("deptName", getDeptName())
                .append("title", getTitle())
                .append("doctId", getDoctId())
                .append("doctName", getDoctName())
                .append("doctType", getDoctType())
                .append("doctSkill", getDoctSkill())
                .append("fCode", getfCode())
                .append("nCode", getnCode())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
