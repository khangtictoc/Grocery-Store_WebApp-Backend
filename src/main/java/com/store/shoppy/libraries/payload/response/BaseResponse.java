package com.store.shoppy.libraries.payload.response;

import lombok.Data;

@Data
public class BaseResponse {
    int statusCode;
    String msg;
    Object data;

    public BaseResponse(int statusCode, String msg, Object data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }
}
