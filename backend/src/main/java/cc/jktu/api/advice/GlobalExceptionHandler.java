package cc.jktu.api.advice;

import cc.jktu.api.dto.CommonResponse;
import cc.jktu.api.exception.AppException;
import cc.jktu.api.exception.NotFoundException;
import cc.jktu.api.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse<?> handle(UnauthorizedException e) {
        return CommonResponse.noContent(e.getLocalizedMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handle(NotFoundException e) {
        return CommonResponse.noContent(e.getLocalizedMessage());
    }

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> handle(AppException e) {
        return CommonResponse.noContent(e.getLocalizedMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handle(NoHandlerFoundException e) {
        return CommonResponse.noContent(e.getLocalizedMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<?> handle(Throwable e) {
        e.printStackTrace();
        return CommonResponse.noContent(HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

}
