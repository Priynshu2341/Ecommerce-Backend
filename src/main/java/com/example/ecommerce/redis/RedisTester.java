package com.example.ecommerce.redis;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTester {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void testRedis() {
        System.out.println("=== RedisTester @PostConstruct started ===");

        try {
            String key = "test:time";
            String value = "Redis Connected Successfully at " + System.currentTimeMillis();

            redisTemplate.opsForValue().set(key, value);
            String retrieved = redisTemplate.opsForValue().get(key);

            System.out.println("✅ Redis Test SUCCESS!");
            System.out.println("   Key   : " + key);
            System.out.println("   Value : " + retrieved);

        } catch (Exception e) {
            System.err.println("❌ Redis Test FAILED!");
            System.err.println("   Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}