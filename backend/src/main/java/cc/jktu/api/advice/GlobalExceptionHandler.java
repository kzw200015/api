package cc.jktu.api.advice;

import cc.jktu.api.dto.CommonResponse;
import cc.jktu.api.exception.NotFoundException;
import cc.jktu.api.exception.UnauthorizedException;
import cc.jktu.api.exception.WrongPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<?> handleWrongWrongPasswordException(WrongPasswordException ex) {
        return CommonResponse.noContent(ex.getLocalizedMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse<?> handleUnauthorizedException(UnauthorizedException ex) {
        return CommonResponse.noContent(ex.getLocalizedMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handleNotFoundException(NotFoundException ex) {
        return CommonResponse.noContent(ex.getLocalizedMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse<?> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return CommonResponse.noContent(ex.getLocalizedMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<?> handleThrowable(Throwable ex) {
        log.error(ex.getLocalizedMessage());
        ex.printStackTrace();
        return CommonResponse.noContent(HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

}
