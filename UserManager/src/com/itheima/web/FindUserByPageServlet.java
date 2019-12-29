package com.itheima.web;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求参数的字符集
        request.setCharacterEncoding("utf-8");
        //获取请求参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //如果没有选当前页码和每页显示的条目数,则设置默认值为第一页和每页5行
        if(currentPage==null || "".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null || "".equals(rows)){
            rows="5";
        }
        //创建Map集合,封装搜索框的内容
        Map<String, String> condition = new HashMap<String,String>();
        condition.put("name",request.getParameter("name"));
        condition.put("address",request.getParameter("address"));
        condition.put("email",request.getParameter("email"));
        //System.out.println(condition);
        //调用service方法,获取分页的数据对象
        UserService service = new UserServiceImpl();
        PageBean<User> pb= service.findUserByPage(currentPage,rows,condition);
        //System.out.println(pb);
        //将PageBean数据存入request域中
        request.setAttribute("condition",condition);
        request.setAttribute("pb",pb);
        //请求转发到list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
