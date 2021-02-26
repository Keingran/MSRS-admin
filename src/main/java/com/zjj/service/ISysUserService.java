package com.zjj.service;

import com.zjj.dto.SysUser;
import com.zjj.dto.SysUserAuth;

import java.util.List;

/**
 * 用户 业务层
 */
public interface ISysUserService {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过手机号查询用户
     *
     * @param phone 手机号
     * @return 用户对象信息
     */
    public SysUser selectUserByPhone(String phone);


    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 查询用户认证信息
     *
     * @param userId 用户id
     * @return 认证信息
     */
    SysUserAuth getUserAuth(Long userId);
}
