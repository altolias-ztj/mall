package com.woniuxy.mall.vo;

import com.woniuxy.mall.mallenum.ResponseCode;
import lombok.Data;

@Data
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public ResponseResult(T data) {
        this(ResponseCode.SUCCESS);
        this.data = data;
    }

    public ResponseResult(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public static ResponseResult<Void> ok() {
        return new ResponseResult<>(ResponseCode.SUCCESS);
    }
}
