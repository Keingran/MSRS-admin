package com.zjj.manager.factory;

import com.zjj.common.constant.Constants;
import com.zjj.dto.SysLoginLog;
import com.zjj.service.ISysLoginLogService;
import com.zjj.utils.LogUtils;
import com.zjj.utils.ServletUtils;
import com.zjj.utils.SpringUtils;
import com.zjj.utils.ip.AddressUtils;
import com.zjj.utils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLoginLog(final String username, final String status, final String message,
                                           final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info("[AsyncFactory --> recordLoginLog] 异步工厂任务日志信息：{},{}", s.toString(), args);
                //sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginLog loginLog = new SysLoginLog();
                loginLog.setUserName(username);
                loginLog.setIpAddr(ip);
                loginLog.setLoginLocation(address);
                loginLog.setBrowser(browser);
                loginLog.setOs(os);
                loginLog.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    loginLog.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginLog.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(ISysLoginLogService.class).insertLoginLog(loginLog);
            }
        };
    }

    ///**
    // * 操作日志记录
    // *
    // * @param operLog 操作日志信息
    // * @return 任务task
    // */
    //public static TimerTask recordOper(final SysOperLog operLog) {
    //    return new TimerTask() {
    //        @Override
    //        public void run() {
    //            // 远程查询操作地点
    //            operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
    //            SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
    //        }
    //    };
    //}
}
