package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.exception.NotFoundException;
import cc.jktu.api.blog.exception.WrongPasswordException;
import cc.jktu.api.common.CommonResponse;
import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class BlogExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse handleNotLoginException(NotLoginException ex) {
        return CommonResponse.noContent(HttpStatus.UNAUTHORIZED, "未登录");
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleWrongPasswordException(WrongPasswordException ex) {
        return CommonResponse.noContent(HttpStatus.BAD_REQUEST, "密码错误");
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse handleNotFoundException(NotFoundException ex) {
        return CommonResponse.noContent(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return CommonResponse.noContent(HttpStatus.NOT_FOUND);
    }

}
