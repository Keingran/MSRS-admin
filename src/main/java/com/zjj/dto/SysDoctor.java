package com.zjj.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 医生表 sys_doctor
 */
public class SysDoctor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 医生工号
     */
    private String doctId;

    /**
     * 医生名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 医生职称id
     */
    private Long doctType;

    /**
     * 医生职称
     */
    private String postName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 性别 0男 1女 2未知
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态 0正常 1停用
     */
    private String status;

    /**
     * 状态 0正常 1删除
     */
    private String isDelete;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 分页参数
     */
    private int page;
    private int size;

    private int isManage;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 特长简介
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    public String getDoctId() {
        return doctId;
    }

    public void setDoctId(String doctId) {
        this.doctId = doctId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDoctType() {
        return doctType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setDoctType(Long doctType) {
        this.doctType = doctType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public int getIsManage() {
        return isManage;
    }

    public void setIsManage(int isManage) {
        this.isManage = isManage;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("doctId", getDoctId())
                .append("name", getName())
                .append("password", getPassword())
                .append("deptId", getDeptId())
                .append("deptName", getDeptName())
                .append("doctType", getDoctType())
                .append("postName", getPostName())
                .append("email", getEmail())
                .append("phone", getPhone())
                .append("sex", getSex())
                .append("avatar", getAvatar())
                .append("status", getStatus())
                .append("isDelete", getIsDelete())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("content", getContent())
                .append("remark", getRemark())
                .toString();
    }
}
