package com.candeus.product.common.exception;


import com.candeus.product.common.locale.LocaleMessage;
import com.candeus.product.common.result.ErrorResult;
import com.candeus.product.common.result.Result;
import com.candeus.product.tool.ObjCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author codenj
 * @since 2023/5/4
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final LocaleMessage localeMessage;

    @Autowired
    public GlobalExceptionHandler(LocaleMessage localeMessage) {
        this.localeMessage = localeMessage;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(final BindException e) {
        logStackMsg(e);
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return handleErrorResult(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result violationExceptionHandler(final ConstraintViolationException e) {
        logStackMsg(e);
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return handleErrorResult(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(final MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        log.error("请求[ {} ] {} 的参数校验发生错误", request.getMethod(), request.getRequestURL());
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            log.error("参数 {} = {} 校验错误：{}", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
            result.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        return handleErrorResult(400,message,result);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    public Result customExceptionHandler(final CustomException e) {
        if (ObjCheckUtils.nonBlank(e.getError())) {
            log.error("系统出现异常：{}", e.getError());
        }
        return handleErrorResult(e.getCode(), e.getMessage(), e.getError());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SQLException.class)
    public Result sqlException(final SQLException e) {
        logStackMsg(e);
        return handleErrorResult("数据库操作失败", e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(final NullPointerException e) {
        logStackMsg(e);
        return handleErrorResult(localeMessage.getMessage("common.error.server"), "NullPointerException");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NumberFormatException.class)
    public Result numberFormatException(final NumberFormatException e) {
        return handleErrorResult(localeMessage.getMessage("common.error.server"), "整型数据请勿传字符串");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Result defaultException(final Exception e) {
        logStackMsg(e);
        return handleErrorResult(e.getMessage(), "");
    }

    private Result handleErrorResult(int code, String msg, String error) {
        return new ErrorResult(code, msg, error);
    }

    private Result handleErrorResult(String msg, String error) {
        return handleErrorResult(400, msg, error);
    }

    private Result handleErrorResult(int code, String msg, Object data) {
        return Result.build(400, msg, data);
    }

    private Result handleErrorResult(String msg) {
        return handleErrorResult(msg, "");
    }

    private void logStackMsg(Exception e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (StackTraceElement element : stackArray) {
            sb.append(element.toString()).append("\n");
        }
        log.error(sb.toString());
    }

}
