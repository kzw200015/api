package cc.jktu.api.controller;

import cc.jktu.api.model.vo.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/error")
@RequiredArgsConstructor
public class GlobalErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @RequestMapping
    public CommonResponse error(HttpServletRequest request) {
        final Throwable ex = errorAttributes.getError(new ServletWebRequest(request));
        final HttpStatus httpStatus = getHttpStatus(request);
        final CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(ex != null ? ex.getLocalizedMessage() : httpStatus.name());
        return commonResponse;
    }

    protected HttpStatus getHttpStatus(HttpServletRequest request) {
        final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return HttpStatus.valueOf(((Integer) status));
    }

}
