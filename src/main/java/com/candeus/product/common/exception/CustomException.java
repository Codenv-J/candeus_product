package com.candeus.product.common.exception;

/**
 * @author codenj
 * @since 2023/5/4
 */
public class CustomException extends RuntimeException {

    private int code = 500;
    private String error = "";


    public CustomException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public CustomException setCode(int code) {
        this.code = code;
        return this;
    }

    public String getError() {
        return error;
    }

    public CustomException setError(String error) {
        this.error = error;
        return this;
    }

    /**
     * 必须排查的异常
     */
    public static CustomException systemError(String error) {
        return new CustomException("系统异常，请联系管理员").setError(error);
    }
}
