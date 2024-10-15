package com.lingnuokeji.BasicSpringBoot.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;// 用户序号
    private String username; //用户名
    private String password; //密码
    private String device; //设备号
    private String screenName;// 屏幕名称
}
