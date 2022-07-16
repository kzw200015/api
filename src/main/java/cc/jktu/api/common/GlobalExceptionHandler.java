package cc.jktu.api.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return CommonResponse.noContent(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
