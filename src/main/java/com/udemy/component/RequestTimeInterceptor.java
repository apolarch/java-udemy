package com.udemy.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("requestTimeeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
    private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

    //PRIMERO
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("StartTime", System.currentTimeMillis());
        return true;
    }

    //SEGUNDO
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        long startTime = (long) request.getAttribute("StartTime");
        LOG.info("URL to: '" + request.getRequestURL().toString() + "' in: '" + (System.currentTimeMillis()-startTime) + "ms'");
    }

}
