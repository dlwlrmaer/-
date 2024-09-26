package com.atguigu.headline.filters;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * ClassName: LoginFilter
 * Package: com.atguigu.headline.filters
 * Description:
 *
 * @Author ÉÐ¹è¹È-ËÎºì¿µ
 * @Create 2024/9/26 16:15
 * @Version 1.0
 */
@WebFilter("/headline/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("token");
        boolean flag = false;

        if (null != token){
            boolean expiration = JwtHelper.isExpiration(token);
            if(!expiration){
                flag = true;
            }
        }
        if (flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            WebUtil.writeJson((HttpServletResponse) servletResponse, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }
}
