package com.book.RepeatSubmission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by ${罗显} on 2018/10/22
 */
@Component
public class BaseRedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key,Object data,Long timeout){
        if(data instanceof String){
            String value = (String) data;
            stringRedisTemplate.opsForValue().set(key,value);
        }
        if(timeout != null){
            stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        }
    }

    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key){
        stringRedisTemplate.delete(key);
    }
}
