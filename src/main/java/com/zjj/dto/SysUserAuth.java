package com.zjj.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证表 sys_user_auth
 */
public class SysUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 用户编号
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 状态 0可用 1已删除
     */
    private String isDelete;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                .append("idCardNo", getIdCardNo())
                .append("idCardType", getIdCardType())
                .append("idCardTypeView", getIdCardTypeView())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("isDelete", getIsDelete())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
