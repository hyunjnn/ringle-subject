package org.example.ringlesubject.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "성공", data);
    }

    public static ApiResponse<?> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }
}