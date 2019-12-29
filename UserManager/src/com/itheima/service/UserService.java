package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 添加用户的方法
     * @param user
     */
    public void addUser(User user);

    /**
     *查询所有用户的方法
     * @return
     */
    public List<User> findAll();

    /**
     *根据用户id删除用户数据的方法
     * @param id
     */
    public void delUserById(String id);

    /**
     * 根据用户id查询单个用户信息
     * @return
     */
    public User findUserById(String id);

    /**
     * 修改用户信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除选中的用户记录,通过id数组
     * @param ids
     */
    public void delSeleteUser(String[] ids);

    /**
     * 分页查询用户
     * @param currentPage 当前页码数
     * @param rows 每页显示的行数
     * @return 分页数据封装类对象
     */
    public PageBean<User> findUserByPage(String currentPage, String rows);

    /**
     * 根据条件分页查询用户
     * @param currentPage 当前页码数
     * @param rows 每页显示的行数
     * @param condition 搜索框输入的查询条件
     * @return 分页数据封装类对象
     */
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String> condition);
}
