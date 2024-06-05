package com.pms.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {
    private T payload;
    private int status;
    private String message;

    public ApiResponse(){}

    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }
}
