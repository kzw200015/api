package cc.jktu.api.advice;

import cc.jktu.api.annotation.NeedAuth;
import cc.jktu.api.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            NeedAuth needAuth = handlerMethod.getMethodAnnotation(NeedAuth.class);

            if (needAuth == null) {
                needAuth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(NeedAuth.class);
            }

            if (needAuth != null) {
                final HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute("userId") == null) {
                    throw new UnauthorizedException();
                }
            }
        }
        return true;
    }

}
