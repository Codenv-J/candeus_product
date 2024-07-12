package com.candeus.product.common.exception;

/**
 * 定义的错误码，及默认提示语
 *
 * @author codenj
 * @since 2020/3/18
 */
public enum ErrorCode {
    /**
     * 未登录,跳转到登录页
     */
    NOT_LOGIN(401, "身份认证失败,请重新登录");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
