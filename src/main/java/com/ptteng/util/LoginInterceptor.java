package com.ptteng.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by litao on 2017/3/3.
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * preHandle 方法是进行处理器拦截用的,该方法将在Controller处理前调用,SpringMVC中的Interceptor拦截是链式的,可以同时存在
     * 多个Interceptor,然后SpringMVC会根据声明的前后顺序一个接一个的执行,而且所有的Interceptor中的preHandle方法都会在Controller
     * 方法调用之前调用.SpringMVC的这种Interceptor链式结构也是可以进行中断的,这种中断方式是令preHandle的返回值为false
     * 当preHandle的返回值为false的时候整个请求就结束了
     * */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception{


        //如果已经登录,就直接 chain.doFilter(request, response)放行
//        if (request.getSession().getAttribute("student") != null){
//
//            return true;
//        }

        //得到用户带过来的token的cookie
        String value = null;
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies!=null && i < cookies.length; i ++){
            if (cookies[i].getName().equals("token")){
                value = cookies[i].getValue();
            }
        }

        if (value == null){
            response.sendRedirect("/task5/login");
            return false;
        }else {
            return true;
        }
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mv) throws Exception{

//        Cookie[] cookies = request.getCookies();
//        int res = 0;
//        if (cookies != null){
//            for (int i = 0; i<cookies.length; i++){
//                Cookie cookie = cookies[i];
//                if (cookie.getName().equals("status") & cookie.getValue().equals("login")){
//                    res = 1;
//
//
//                }
//            }
//        }
//
//        if (res == 0){
//            Cookie cookie = new Cookie("status","loginout");
//            response.addCookie(cookie);
//            response.sendRedirect("/task5/login");
//        }






    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object var3, Exception var4) throws Exception{

    }

}
