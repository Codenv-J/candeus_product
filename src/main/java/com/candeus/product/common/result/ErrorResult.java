package com.candeus.product.common.result;

import lombok.Data;

/**
 * 带异常信息的返回结果
 *
 * @author codenj
 * @since 2023/9/11
 */
@Data
public class ErrorResult extends Result {
    public ErrorResult(int code, String msg, String error) {
        super(code,msg,error);
    }
}
