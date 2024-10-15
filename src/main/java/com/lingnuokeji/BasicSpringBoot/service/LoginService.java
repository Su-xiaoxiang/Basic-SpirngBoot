package com.lingnuokeji.BasicSpringBoot.service;

import com.lingnuokeji.BasicSpringBoot.pojo.DTO.UserDTO;
import com.lingnuokeji.BasicSpringBoot.pojo.VO.UserVO;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/15 21:55
 * @Version 1.0
 */
public interface LoginService {
    /**
     * 登录接口
     *
     * @param userDTO 用户信息
     * @return
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/10/15 21:56
     */
    UserVO login(UserDTO userDTO );
}
