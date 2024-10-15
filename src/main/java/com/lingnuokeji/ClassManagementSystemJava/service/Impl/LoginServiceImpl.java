package com.lingnuokeji.ClassManagementSystemJava.service.Impl;

import com.lingnuokeji.ClassManagementSystemJava.mapper.LoginMapper;
import com.lingnuokeji.ClassManagementSystemJava.pojo.DTO.UserDTO;
import com.lingnuokeji.ClassManagementSystemJava.pojo.VO.UserVO;
import com.lingnuokeji.ClassManagementSystemJava.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/15 21:55
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    /**
     * 登录接口
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/10/15 21:58
     */
    @Override
    public UserVO login(UserDTO userDTO) {
        return loginMapper.login(userDTO);
    }
}
