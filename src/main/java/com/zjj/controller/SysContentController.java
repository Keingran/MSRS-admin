package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjj.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class SysContentController {

    @GetMapping("/hospital")
    public Result getContent() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", " ## 北京协和医院预约挂号须知\n为方便您早日就医康复，请您认真阅读预约挂号须知：\n#### 一、预约实名制：\n \n统一平台电话预约和网上预约挂号均采取实名制注册预约，请您如实提供就诊人员的真实姓名、有效证件号（身份证、护照）、性别、手机号码、社保卡号等基本信息。\n \n#### 二、预约挂号：\n \n按照北京市卫健委统一平台要求，预约挂号规则如下：\n \n* 在同一自然日，同一医院，同一科室，同一就诊单元，同一就诊人，可以预约最多1个号源；\n* 在同一自然周，同一就诊人，可以预约最多14个号源；\n* 在同一自然月，同一就诊人，可以预约最多20个号源；\n* 在同一自然季度，同一就诊人，可以预约最多30个号源。\n \n#### 三、取消预约：\n已完成预约的号源，如需办理退号，至少在就诊前一工作日14:00前通过网站、微信公众号、114电话等平台预约渠道进行取消预约。\n#### 四、爽约处理：\n \n如预约成功后患者未能按时就诊且不办理取消预约号视为爽约，同一患者在自然年内爽约规则如下：\n \n* 累计爽约3次，自3次爽约日起，90天内不允许通过114平台进行预约挂号；\n* 累计爽约6次，自6次爽约日起，180天内不允许通过114平台进行预约挂号。"
        );
        return Result.success(jsonObject);
    }

}
