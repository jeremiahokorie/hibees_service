package com.hibees_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private String message;
    private String status;
    private T data;


    public static <T> ApiResponse<T> of(String message,String status, T data){
        return ApiResponse.<T>builder()
                .message(message)
                .status(status)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(T data){
        return ApiResponse.<T>builder()
                .message("Succeful")
                .status("00")
                .data(data)
                .build();
    }
}
