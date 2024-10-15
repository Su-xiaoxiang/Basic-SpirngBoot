package com.lingnuokeji.classmanagementsystemjava.service;

import com.lingnuokeji.classmanagementsystemjava.pojo.DTO.UserDTO;
import com.lingnuokeji.classmanagementsystemjava.pojo.VO.UserVO;
import jakarta.servlet.http.HttpServletRequest;

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
