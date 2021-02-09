package com.zjj;

import com.zjj.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsrsAdminApplicationTests {
    @Autowired
    private RedisCache redisCache;

    @Test
    public void TestB(){
        Long dept = redisCache.getExpire("phone");
        System.out.println(dept);
    }

    @Test
    public void TestA() {

        String accessKeyId = "LTAI4GC8gefeni21df97wniZ";
        String secret = "0lCNEUKrwEkpHp4qySinQnCEIxhjg5";

        //// 连接阿里云
        //DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
        //IAcsClient client = new DefaultAcsClient(profile);
        //
        //// 构建请求
        //CommonRequest request = new CommonRequest();
        //request.setSysMethod(MethodType.POST);
        //request.setSysDomain("dysmsapi.aliyuncs.com");
        //request.setSysVersion("2017-05-25");
        //request.setSysAction("SendSms");
        //
        //// 自定义参数 (手机号,签名,模板,验证码)
        //request.putQueryParameter("PhoneNumbers", "15915101094");
        //request.putQueryParameter("SignName", "SISE预约挂号");
        //request.putQueryParameter("TemplateCode", "SMS_208980294");
        ////构建一个短信验证码
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("code",2233);
        //request.putQueryParameter("TemplateParam", jsonObject.toJSONString());
        //
        //try {
        //    CommonResponse response = client.getCommonResponse(request);
        //    System.out.println(response.getData());
        //} catch (ServerException e) {
        //    e.printStackTrace();
        //} catch (ClientException e) {
        //    e.printStackTrace();
        //}
    }
}
