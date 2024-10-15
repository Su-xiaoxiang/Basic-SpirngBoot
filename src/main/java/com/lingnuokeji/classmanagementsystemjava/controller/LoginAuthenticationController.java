package com.lingnuokeji.classmanagementsystemjava.controller;

import com.lingnuokeji.classmanagementsystemjava.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/9/6 13:02
 * @Version 1.0
 */
@RestController
@Slf4j
public class LoginAuthenticationController {
    /**
     * 登录认证接口
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/9/10 16:19
     * @return Result
     */

    @PostMapping("/UserLoginAuthentication")
    public Result loginAuthentication()
    {
        return Result.success();
    }
}
