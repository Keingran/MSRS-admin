package com.zjj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

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
    private Long deptId;

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
    private String postName;

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
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 分页参数
     */
    private int page;
    private int size;

    /**
     * 版本号
     */
    private int version;

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

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
                .append("postName", getPostName())
                .append("doctSkill", getDoctSkill())
                .append("fCode", getfCode())
                .append("nCode", getnCode())
                .append("status", getStatus())
                .append("version", getVersion())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
