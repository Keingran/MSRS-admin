package com.zjj.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 就诊卡表 sys_patient_card
 */
public class SysPatientCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 就诊卡No
     */
    private String cardNo;

    /**
     * 就诊卡类型
     */
    private String cardType;

    /**
     * 就诊卡名称
     */
    private String cardTypeView;

    /**
     * 付费卡类型
     */
    private String medicareType;

    /**
     * 付费卡名称
     */
    private String medicareTypeView;

    /**
     * 就诊人编号
     */
    private Long patientId;

    /**
     * 状态 0正常 1删除
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardTypeView() {
        return cardTypeView;
    }

    public void setCardTypeView(String cardTypeView) {
        this.cardTypeView = cardTypeView;
    }

    public String getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(String medicareType) {
        this.medicareType = medicareType;
    }

    public String getMedicareTypeView() {
        return medicareTypeView;
    }

    public void setMedicareTypeView(String medicareTypeView) {
        this.medicareTypeView = medicareTypeView;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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
                .append("cardNo", getCardNo())
                .append("cardType", getCardType())
                .append("cardTypeView", getCardTypeView())
                .append("medicareType", getMedicareType())
                .append("medicareTypeView", getMedicareTypeView())
                .append("patientId", getPatientId())
                .append("isDelete", getIsDelete())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
