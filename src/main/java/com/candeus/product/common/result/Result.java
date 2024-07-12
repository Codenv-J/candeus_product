package com.candeus.product.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 统一返回结果
 *
 * @author codenj
 * @since 2023/3/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {



    private int code;
    private String msg;

    private Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static Result ok(){
        return new Result(200, "成功", null);
    }
    public static Result ok(Object data){
        return new Result(200, "成功", data);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(200, "成功", data);
    }
    public static Result fail(String errorMsg){
        return new Result(400, errorMsg,null);
    }
    public static Result fail(String errorMsg,Object data){
        return new Result(400, errorMsg,data);
    }
    public static Result build(Integer code,String msg,Object data){
        return new Result(code, msg,data);
    }

}

