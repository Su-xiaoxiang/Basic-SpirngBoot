package com.lingnuokeji.ClassManagementSystemJava.mapper;

import com.lingnuokeji.ClassManagementSystemJava.pojo.DTO.UserDTO;
import com.lingnuokeji.ClassManagementSystemJava.pojo.VO.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @UserName 程序员_Suxiaoxiang
* @date 2024/10/15 22:07
* @Version 1.0
*/
@Mapper
public interface LoginMapper {
    /**
     * 登录接口
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/10/15 22:13
     */
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
     UserVO login(UserDTO userDTO);
}
