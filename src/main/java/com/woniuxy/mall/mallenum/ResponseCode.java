package com.woniuxy.mall.mallenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, "ok"),
    FAIL(500, "ok"),
    NOT_LOGIN(201, "未登录"),
    NO_AUTHED(403, "未授权");
    private Integer code;
    private String msg;

/*    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }*/
}
