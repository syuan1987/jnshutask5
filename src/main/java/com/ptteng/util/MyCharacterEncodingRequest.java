package com.ptteng.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by litao on 2017/3/5.
 */
public class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    public MyCharacterEncodingRequest(HttpServletRequest request){
        super(request);
        this.request = request;
    }

    /**
     * 重写getParameter方法
     * */

    public String getParameter(String name) {
        try {
            String value = this.request.getParameter(name);
            if (value == null){
                return null;
            }
            //如果不是以get方式提交数据的,直接返回获取到的值
            if (this.request.getMethod().equalsIgnoreCase("get")){
                return value;
            } else {
                //如果是以get方式提交数据的,就对获取的值进行转码处理
                value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
//                value = new String(value.getBytes("UTF-8"),this.request.getCharacterEncoding());
                return value;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
