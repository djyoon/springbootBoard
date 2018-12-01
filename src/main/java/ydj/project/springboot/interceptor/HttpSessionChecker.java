package ydj.project.springboot.interceptor;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ydj.project.springboot.VO.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


/**
 * Created by djyoon on 2018-11-27.
 */

@Component
public class HttpSessionChecker implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession httpSession = request.getSession();
        if((httpSession.getAttribute("loginUser") != null)){
            return true;
        } else {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<script>");
            printWriter.println("alert('로그인 후 이용가능합니다.');");
            printWriter.println("location.href='/'");
            printWriter.println("</script>");

            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
