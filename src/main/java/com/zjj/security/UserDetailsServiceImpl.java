package com.zjj.security;

import com.zjj.dto.SysUser;
import com.zjj.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 自定义用户验证处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //SysUser user = userService.selectUserByUserName(username);
        return null;
    }
}
