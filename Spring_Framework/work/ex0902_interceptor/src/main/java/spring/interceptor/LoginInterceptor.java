package spring.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 반환값 준비
        boolean result=true;

        // 로그인 체크를 해서 로그인이 안된 상태이면 result에 false를 저장한다. 로그인 체크를 위해 httpsession을 얻어내자.
        HttpSession session= request.getSession();

        Object obj = session.getAttribute("mvo");

        if(obj==null){
            result=false;
            response.sendRedirect("/login");
        }


        return result;// true를 반환한다면 원래 가려고 했었던 경로로 진행을 하지만 false를 반환한다면 진행불가.
    }
}
