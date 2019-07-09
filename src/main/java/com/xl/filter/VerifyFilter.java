package com.xl.filter;

import org.springframework.security.authentication.DisabledException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      if (isProtecteUrl(request)){
          String verifyCode = request.getParameter("verifyCode");
          if (!validateVerify(verifyCode)){
              //手动设置异常
              request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION",new DisabledException("验证码输入错误"));
              //转发到错误url
              request.getRequestDispatcher("/login/error").forward(request,response);
          }else {
              filterChain.doFilter(request,response);
          }
      }else {
          filterChain.doFilter(request,response);
      }
    }

    private boolean validateVerify(String inputVerify){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 不分区大小写
       //这个validateCode 是在servlet 中存入 session 的名字
       String validateCode = ((String) request.getSession().getAttribute("validateCode")).toLowerCase();
       inputVerify = inputVerify.toLowerCase();
        System.out.println("验证码："+validateCode+"用户输入"+inputVerify);
        return validateCode.equals(inputVerify);
    }

    /**
     * 拦截、login 的POST请求
     * @param request
     * @return
     */
    private boolean isProtecteUrl(HttpServletRequest request){
      return "POST".equals(request.getMethod())&&pathMatcher.match("/login",request.getServletPath());
    }
}
