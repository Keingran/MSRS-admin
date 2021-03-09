package com.zjj.mapper;


import com.zjj.dto.SysUser;
import com.zjj.dto.SysUserAuth;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询用户认证信息
     *
     * @param userId 用户id
     * @return 认证信息
     */
    SysUserAuth getUserAuth(Long userId);

    /**
     * 新增用户实名认证
     *
     * @param userAuth 实名认证信息
     */
    int insertUserAuth(SysUserAuth userAuth);

    /**
     * 用户认证
     *
     * @param userId   用户id
     * @param userName 用户名称
     * @return 结果
     */
    int updateUserAuth(@Param("userId") Long userId, @Param("username") String username);
}
