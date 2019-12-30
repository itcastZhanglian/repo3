package com.itheima.web;

import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 添加用户
 */
@WebServlet("/adduserServlet")
public class AdduserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = new User();
        //方法一:体力活
        //获取表单提交的数据
        /*String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        //封装数据到User对象
        user.setName(name);
        user.setGender(gender);
        user.setAge(Integer.parseInt(age));
        user.setAddress(address);
        user.setQq(qq);
        user.setEmail(email);
        */
        //方法而调用BeanUtil方法一次性封装数据
        try {
            BeanUtils.populate(user,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用UserDao方法,将User方法传入数据库中
        UserService service = new UserServiceImpl();
        service.addUser(user);

        //添加完后,跳转回list.html页面,使用重定向
        //System.out.println(request.getContextPath()+"/list.jsp");
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
