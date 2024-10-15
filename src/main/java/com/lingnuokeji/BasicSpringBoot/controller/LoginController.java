package com.lingnuokeji.BasicSpringBoot.controller;

import com.lingnuokeji.BasicSpringBoot.pojo.DTO.UserDTO;
import com.lingnuokeji.BasicSpringBoot.pojo.Result;
import com.lingnuokeji.BasicSpringBoot.pojo.VO.UserVO;
import com.lingnuokeji.BasicSpringBoot.service.LoginService;
import com.lingnuokeji.BasicSpringBoot.utils.IpUtil;
import com.lingnuokeji.BasicSpringBoot.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/15 21:50
 * @Version 1.0
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO, HttpServletRequest request){
        // 获取用户登录时的 IP 地址
        String ip = IpUtil.getIpAddr(request);
        String newIp = ip.replace(":", "-");
        UserVO userVo=loginService.login(userDTO);
        log.info("用户登录信息为: {}", userDTO);
        log.info("用户登录IP地址为: {}", newIp);
        if (userVo != null) {
            String userId = userVo.getId();
            // 生成新的 JWT 令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userVo.getId());
            claims.put("username", userVo.getUsername());
            String newJwt = JwtUtils.generateJwt(claims);
            newJwt = newJwt.trim(); // 去除前后空白字符
            String newUserId = userVo.getId();
            userDTO.setId(newUserId);
            // 存储新的 JWT 和用户信息到缓存，覆盖掉之前的旧 JWT
            jwtUtils.storeIpInCache(newIp, userDTO, newJwt);
            log.info("生成的JWT令牌为: {}", newJwt);
            log.info("用户登录成功");
            // 返回新的登录信息
            Map<String, Object> userLoginInfo = new HashMap<>();
            userLoginInfo.put("userId", userId);
            userLoginInfo.put("rolesid", userVo.getRolesid());
            userLoginInfo.put("token", newJwt);
            return Result.success(userLoginInfo);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
