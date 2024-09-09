package com.github.cuidok.oa.server.web.model;

import lombok.Data;

@Data
public class BasicResponse<T> {

    private T data;

    private String code;

    public static <T> BasicResponse<T> success(T data) {
        BasicResponse<T> response = new BasicResponse<>();
        response.setData(data);
        return response;
    }

    public static <T> BasicResponse<T> fail(String code, T data) {
        BasicResponse<T> response = new BasicResponse<>();
        response.setCode(code);
        return response;
    }
}
