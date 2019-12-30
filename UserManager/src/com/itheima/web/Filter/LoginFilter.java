package com.itheima.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = {"/list.jsp","/add.jsp","/update.jsp","/findUserByPageServlet","/findUserServlet","/userListServlet","/delUserServlet","/adduserServlet","/delSelectedServlet","/updateUserServlet"},dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        Object manager = request.getSession().getAttribute("manager");
        if(manager!=null){
            chain.doFilter(req, resp);
        }else {
            request.setAttribute("errorMsg","您还未登录,无权查看该页面,请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
