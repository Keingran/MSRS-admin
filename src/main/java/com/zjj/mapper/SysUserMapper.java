package com.zjj.mapper;


import com.zjj.dto.SysUser;

/**
 * 用户表 数据层
 */
public interface SysUserMapper {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);


    /**
     * 通过手机查询用户
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
}
