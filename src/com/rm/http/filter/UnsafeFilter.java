package com.rm.http.filter;

import com.rm.http.dto.UserDto;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin")
public class UnsafeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserDto userDto = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (userDto != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
           ((HttpServletResponse) servletResponse).sendRedirect("/registration");
        }
    }
}
