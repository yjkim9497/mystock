package com.stock.mystock.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setData(String key, String value, Long expiredTime) {
        redisTemplate.opsForValue()
                .set(key, value, expiredTime, TimeUnit.MILLISECONDS);

    }

    public String getData(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public void appendData(String key, String value) {
        redisTemplate.opsForValue()
                .append(key, value);
    }
}
