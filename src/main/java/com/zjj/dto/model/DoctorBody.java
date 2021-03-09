package com.zjj.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zjj.dto.SysDoctor;
import com.zjj.dto.SysUser;

import java.util.Set;


public class DoctorBody extends LoginUser {

    /**
     * 用户信息
     */
    private SysDoctor user;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    public SysDoctor getDoctor() {
        return user;
    }

    public void setDoctor(SysDoctor user) {
        this.user = user;
    }

    @Override
    public Set<String> getPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String getUsername() {
        return user.getName();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public DoctorBody() {
    }

    public DoctorBody(SysDoctor user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

}
