package com.zjj.Test;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.RsaUtils;
import com.zjj.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestWeb {


    @PostMapping("/login")
    public Result login(@RequestBody JSONObject dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpUtils.getRequestPlayLoad(request);
        HttpUtils.getStringFromStream(request);
        HttpUtils.getMap(request);
        return Result.success(dto);
    }

}
