package com.zjj.service.impl;

import com.zjj.dto.SysUser;
import com.zjj.dto.SysUserAuth;
import com.zjj.mapper.SysUserMapper;
import com.zjj.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户信息 服务层处理
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过手机查询用户
     *
     * @param phone 手机号
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user) {
        // 新增用户信息
        return userMapper.insertUser(user);
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 查询用户认证信息
     *
     * @param userId 用户id
     * @return 认证信息
     */
    @Override
    public SysUserAuth getUserAuth(Long userId) {
        return userMapper.getUserAuth(userId);
    }

    /**
     * 新增用户实名认证
     *
     * @param userAuth 实名认证信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertUserAuth(SysUserAuth userAuth) {
        return userMapper.insertUserAuth(userAuth);
    }

    /**
     * 用户认证
     *
     * @param userId   用户id
     * @param username 用户名称
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUserAuth(Long userId, String username) {
        return userMapper.updateUserAuth(userId, username);
    }
}
