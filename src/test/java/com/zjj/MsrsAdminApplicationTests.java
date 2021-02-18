package com.zjj;


import com.zjj.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class MsrsAdminApplicationTests {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void TestA(){
        redisTemplate.opsForValue().get("login_tokens:f3078afb-793d-45dd-a020-b3ce0b8c1879");
    }

}
