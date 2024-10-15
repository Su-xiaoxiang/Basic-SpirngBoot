package com.lingnuokeji.BasicSpringBoot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    //默认成功响应
    public static Result success() {
        return new Result(200, "success", null);
    }

    //查询 成功响应
    //有查询的结果的响应
    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    //失败响应
    //默认失败的响应
    public static Result error(String msg) {
        return new Result(500, msg, null);
    }
}
