package com.glb.bootcamp.interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Logger extends HandlerInterceptorAdapter{

    HttpServletRequest request;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("RoutinesEndpoint.txt",true));
        
        Date date = new Date();
        
        this.request = request;
        writer.append(date+" "+this.request.getRequestURI()+" "+this.request.getMethod()+" "+this.request.getRemoteAddr());
        writer.newLine();
        writer.close();
        return true;
    }

}