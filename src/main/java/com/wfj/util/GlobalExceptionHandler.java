package com.wfj.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 所有异常报错
     * 
     */
    @ExceptionHandler(value=Exception.class)  
    public String allExceptionHandler(HttpServletRequest request,  
            Exception exception) throws Exception  
    {  
        exception.printStackTrace();
        System.out.println("我报错了："+exception.getLocalizedMessage());
        System.out.println("我报错了："+exception.getCause());
        System.out.println("我报错了："+exception.getSuppressed());
        System.out.println("我报错了："+exception.getMessage());
        System.out.println("我报错了："+exception.getStackTrace());
        return "服务器异常，请联系管理员！";  
    }  

}
