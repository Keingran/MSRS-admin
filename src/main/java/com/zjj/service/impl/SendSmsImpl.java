package com.zjj.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.zjj.common.constant.Constants;
import com.zjj.common.constant.HttpStatus;
import com.zjj.exception.CustomException;
import com.zjj.redis.RedisCache;
import com.zjj.service.SendSms;
import com.zjj.utils.UUID.IdUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class SendSmsImpl implements SendSms {
    private static final Logger log = LoggerFactory.getLogger(SendSmsImpl.class);

    @Value("${qcloudSms.appId}")
    private int appid;

    @Value("${qcloudSms.appKey}")
    private String appkey;

    @Value("${qcloudSms.smsSign}")
    private String smsSign;

    @Value("${qcloudSms.templateId}")
    private int templateId;

    /**
     * @param phone  手机号
     * @param params 模板参数(验证码 + 过期时间)
     * @return 发送短信结果 true=成功, false=失败
     */
    @Override
    public boolean send(String phone, String[] params) {

        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, templateId, params, smsSign, "", "");
            log.info("短信发送结果：" + result.result + "-" + result.errMsg);
            if (result.result != 0) {
                throw new CustomException(result.errMsg, result.result);
            }
            return true;
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return false;
    }
}
