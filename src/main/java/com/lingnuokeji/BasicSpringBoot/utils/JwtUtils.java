package com.lingnuokeji.BasicSpringBoot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingnuokeji.BasicSpringBoot.pojo.DTO.UserDTO;
import com.lingnuokeji.BasicSpringBoot.pojo.DTO.UserSessionInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtils {


    private final static String signKey = "LingNuo"; // 定义主要的标识
    private static final Long expireDate = 3600000L; // 定义有效期时间为1个小时 毫秒
    @Autowired
    private  RedisTemplate<String, String> redisTemplate;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     */
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public  Claims parseJWT(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     *
     * @param Ip
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/9/4 16:42
     */
     public String getRedisIp(String Ip) {
         log.info("--------------开始进行判断Redis是否含有ip--------------");
         log.info("Redis查询数据的ip:{}", Ip);
         String res = redisTemplate.opsForValue().get(Ip);
         log.info("Redis得到的数据ip为:{}", res);
         log.info("------------判断ip结束--------------");
         return res;
     }
    public void storeIpInCache(String Ip, UserDTO userDTO, String jwt) {
        try {
            log.info("--------------进行Redis存储IP---------------");
            String newIp = Ip.replace(":", "-");
            log.info("开始存储JWT和设备信息，用户ID: {}", userDTO.getId());

            // 构建用户会话信息
            UserSessionInfo sessionInfo = new UserSessionInfo(userDTO.getId(),userDTO.getDevice(),userDTO.getScreenName(),jwt,Ip);

            // 将对象序列化为 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String sessionInfoJson = objectMapper.writeValueAsString(sessionInfo);

            // 存储到 Redis
            redisTemplate.opsForValue().set(userDTO.getId(), sessionInfoJson, 1, TimeUnit.HOURS);
            log.info("JWT和设备信息存储成功，IP地址: {}, 数据为: {}", newIp, sessionInfoJson);
        } catch (JsonProcessingException e) {
            log.error("序列化会话信息时出现异常，用户ID: {}", userDTO.getId(), e);
        } catch (Exception e) {
            log.error("存储JWT和设备信息时出现异常，用户ID: {}", userDTO.getId(), e);
        }
    }
    public UserSessionInfo getSessionInfoFromCache(String ip) throws JsonProcessingException {
        String newIp = ip.replace(":", "-");
        String sessionInfoJson = redisTemplate.opsForValue().get(newIp);

        if (sessionInfoJson != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(sessionInfoJson, UserSessionInfo.class);
        }
        return null;
    }



    public  boolean isJwtValid(String jwt, String userId) {
        try {
            Claims claims = parseJWT(jwt);
            Date expiration = claims.getExpiration();
            // 确保 JWT 没有过期
            if (expiration.before(new Date())) {
                return false;
            }

            // 验证缓存中的 JWT 是否匹配
            String cachedJwt = redisTemplate.opsForValue().get(userId);

                ObjectMapper objectMapper = new ObjectMapper();
                UserSessionInfo sessionInfo = objectMapper.readValue(cachedJwt, UserSessionInfo.class);
                String cachedJwtFromRedis = sessionInfo.getJwt();
                log.info("JWT验证，缓存中的JWT为:{}, 传入的JWT为:{}", cachedJwtFromRedis, jwt);
                return jwt.equals(cachedJwtFromRedis);

        } catch (Exception e) {
            return false;
        }
    }
}
