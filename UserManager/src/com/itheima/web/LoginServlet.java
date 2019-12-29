package com.itheima.web;

import com.itheima.dao.ManagerDao;
import com.itheima.domain.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 登录校验
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request的字符集
        request.setCharacterEncoding("utf-8");

        /*
        * 验证码验证
        *
         */
        //1.获取浏览器输入的验证码,如果不正确的话,则不执行判断用户名和密码的操作
        String verifycode = (String) request.getParameter("verifycode");
        //2.获取服务器端保存的验证码答案
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        //3.验证码只能用一次,所以使用后删除session
        request.getSession().removeAttribute("checkCode");
        //4.判断,如果验证码不正确,则不执行验证用户名和密码,直接跳转回登录页
        //注意,用户不输入时,verifycode是空字符串,而不是null,另外为了避免空指针,将checkcode放在后面
        if (!verifycode.equals(checkCode)){
            //5.如果验证码不正确,保存错误信息,跳转会登录页面
            request.setAttribute("errorMsg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;//后面代码不再执行
        }
        /*
         * 验证码验证
         */


        /*
         * 用户名和密码验证
         */
        //获取浏览器的请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkbox = request.getParameter("remember");
        //调用ManagerDao类中的方法,判断数据库中是否有匹配请求参数的数据
        ManagerDao managerDao = new ManagerDao();
        Manager manager = managerDao.login(username,password);
        //判断,如果查询结果封装的manager是null,则登录失败,反之,则登录成功
        if (manager==null){
            //如果登录失败,将失败信息封装到request中,因为只跳转一次到登录页面,所以用request域,并且用请求转发
            /*response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<h1 align='center'>登录失败,请核对用户名或密码</h1>");*/
            request.setAttribute("errorMsg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            /*如果登录页勾选了"记住用户名",并且登录成功,则存储cookie到浏览器中,
            cookie的名称为复选框的name , 并设置cookie有效时长为7天*/
            //如果登录页没有勾选,则删除cookie
            Cookie cookie = new Cookie("remember",manager.getUsername());
            if ("rem".equals(checkbox)){
                cookie.setMaxAge(60*60*24*7);
            }else{
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);

            //如果登录成功,将manager对象存入session中,因为多次请求间共享数据,所以用Session域
            HttpSession session = request.getSession();
            session.setAttribute("manager",manager);
            //request.getRequestDispatcher("/list.html").forward(request,response);
            /*response.sendRedirect(request.getContextPath()+"/list.jsp"); 改进list页面是假数据,
            所以登录成功后先跳转到sevlet中,获取数据库的user表,再跳转到list.jsp展示数据,如下*/
            response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
