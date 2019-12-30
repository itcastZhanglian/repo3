package com.itheima.web;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据用户id删除数据
 */

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String id = request.getParameter("id");
        //2.调用service方法
        UserService service = new UserServiceImpl();
        service.delUserById(id);
        //3.再次跳转到UserlistServlet查询记录
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
