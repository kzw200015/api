package cc.jktu.api.advice;

import cc.jktu.api.annotation.NeedLogin;
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
            NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);

            if (needLogin == null) {
                needLogin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(NeedLogin.class);
            }

            if (needLogin != null) {
                final HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute("userId") == null) {
                    throw new UnauthorizedException();
                }
            }
        }


        return true;
    }

}
