package cc.jktu.api.controller;

import cc.jktu.api.model.vo.CommonResponse;
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
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
            MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpStatus status = HttpStatus.OK;
        final ResponseStatus responseStatus = returnType.getMethodAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            status = responseStatus.code();
        }

        Object body = bodyContainer.getValue();
        if (!CommonResponse.class.isAssignableFrom(body.getClass())) {
            final CommonResponse resp = new CommonResponse();
            resp.setMessage(status.name());
            resp.setData(body);
            bodyContainer.setValue(resp);
        }
    }

}
