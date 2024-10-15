package com.lingnuokeji.ClassManagementSystemJava.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@SuppressWarnings("ALL")
@Slf4j
@Configuration
public class RedisConfig {
    /**
     * RedisTemplate 配置
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/9/10 16:22
     * @return
     */

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
        log.info("RedisConfig已启动.......");
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);

        log.info("RedisTemplate 配置完成：键值序列化使用 StringRedisSerializer");

        return template;
    }
}
