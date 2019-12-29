package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
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
    public void deleteUser(int id);

    /**
     * 根据用户id查询单个用户信息
     * @return
     */
    public User findById(int id);

    /**
     * 修改用户信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 查询总记录数
     * @return
     */
    public int findTotalCount();

    /**
     * 根据数据的索引和每页查询的行数,查询对应页面的数据集合
     * @param start 数据的开始索引
     * @param rows 每页显示的条目数
     * @return 查询到的页面的数据集合
     */
    public List<User> findByPage(int start, int rows);

    /**
     * 根据条件查询总记录数
     * @return
     */
    public int findTotalCount(Map<String, String> condition);

    /**
     * 根据条件查询,获取分页显示的数据集合
     * @return
     */
    public List<User> findByPage(int start, int rows, Map<String, String> condition);
}
