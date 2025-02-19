package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Logger implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("진입전");
        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
//        filterChain.doFilter(req, res );
//
//        log.info("<<<리턴");



        filterChain.doFilter(req, res);

        var regJson = new String(req.getContentAsByteArray());
        log.info("req: {}", regJson);
        var resJson = new String(res.getContentAsByteArray());
        log.info("res: {}", resJson);

        log.info("<<<리턴");

        res.copyBodyToResponse();

    }
}
