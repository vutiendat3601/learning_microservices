package com.datvutech.licenseservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.datvutech.licenseservice.utils.UserContext;
import com.datvutech.licenseservice.utils.UserContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        UserContextHolder.getContext().setCorrelationId(
                servletRequest.getHeader(
                        UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(
                servletRequest.getHeader(
                        UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(
                servletRequest.getHeader(
                        UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setOrganizationId(
                servletRequest.getHeader(
                        UserContext.ORGANIZATION_ID));
                        
        log.debug("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        chain.doFilter(servletRequest, response);
    }

}
