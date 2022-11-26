package cc.jktu.api.advice;

import cc.jktu.api.dto.CommonResponse;
import cc.jktu.api.exception.NotFoundException;
import cc.jktu.api.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


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

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return CommonResponse.noContent(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
