package cc.jktu.api.common;

import cc.jktu.api.blog.annotation.ResponseMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(@NotNull MappingJacksonValue bodyContainer, @NotNull MediaType contentType, @NotNull MethodParameter returnType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        final HttpStatus status = getStatus(returnType);
        final String message = getMessage(status, returnType);

        Object body = bodyContainer.getValue();
        if (body instanceof CommonResponse _body) {
            if (_body.getStatus() == null) {
                _body.setStatus(status.value());
            }
            if (_body.getMessage() == null) {
                _body.setMessage(message);
            }
        } else {
            final CommonResponse resp = new CommonResponse();
            resp.setStatus(status.value());
            resp.setMessage(message);
            resp.setData(body);
            bodyContainer.setValue(resp);
        }
    }

    protected HttpStatus getStatus(MethodParameter returnType) {
        HttpStatus status = HttpStatus.OK;
        final ResponseStatus responseStatus = returnType.getMethodAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            status = responseStatus.code();
        }
        return status;
    }

    protected String getMessage(HttpStatus status, MethodParameter returnType) {
        String message = status.name();
        final ResponseMessage responseMessage = returnType.getMethodAnnotation(ResponseMessage.class);
        if (responseMessage != null) {
            message = responseMessage.value();
        }
        return message;
    }

}
