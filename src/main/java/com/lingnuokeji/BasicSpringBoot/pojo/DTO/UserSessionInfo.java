package com.lingnuokeji.BasicSpringBoot.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/9/5 23:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionInfo {
    private String id;// 用户序号
    private String device; //设备号
    private String screenName;// 屏幕名称
    private String jwt;
    private String ip;
}
