package com.zjj.dto.model;

import com.zjj.dto.SysOrder;

public class OrderBody extends SysOrder {

    private String orderSmsCode;
    private String phone;

    public String getOrderSmsCode() {
        return orderSmsCode;
    }

    public void setOrderSmsCode(String orderSmsCode) {
        this.orderSmsCode = orderSmsCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
