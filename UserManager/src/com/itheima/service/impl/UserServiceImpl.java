package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.junit.experimental.theories.ParametersSuppliedBy;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 添加用户的方法
     * @param user
     */
    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    /**
     *查询所有用户的方法
     * @return
     */
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     *根据用户id删除用户数据的方法
     * @param id
     */
    @Override
    public void delUserById(String id) {
        dao.deleteUser(Integer.parseInt(id));
    }

    /**
     * 根据用户id查询单个用户信息
     * @return
     */
    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    /**
     * 删除选中的用户记录,通过id数组
     * @param ids
     */
    @Override
    public void delSeleteUser(String[] ids) {
        if(ids!=null && ids.length!=0)
        for (String id : ids) {
            dao.deleteUser(Integer.parseInt(id));
        }
    }

    /**
     * 分页查询用户
     * @param _currentPage 当前页码数
     * @param _rows 每页显示的行数
     * @return 分页数据封装类对象
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        //字符类型转整型
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建PageBean对象
        PageBean<User> pb = new PageBean<>();
        //封装现有的数据currentPage和rows
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //获取总记录数
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);
        //计算总页码数,总页码数:如果总记录数除以每页行数能整除,则总页码数=整除结果,如果不能整除,则总页码数=整除结果+1
        int totalPage = (int)Math.ceil(totalCount*1.0/rows);
        pb.setTotalPage(totalPage);
        //计算每页开始的数据索引,获取每页显示的数据集合
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows);
        pb.setList(list);
        return pb;
    }

    /**
     * 根据条件分页查询用户
     * @param _currentPage 当前页码数
     * @param _rows 每页显示的行数
     * @param condition 搜索框输入的查询条件
     * @return 分页数据封装类对象
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String> condition) {
        //字符类型转整型
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //创建PageBean对象
        PageBean<User> pb = new PageBean<>();
        //封装现有的数据currentPage和rows
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //获取总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //计算总页码数,总页码数:如果总记录数除以每页行数能整除,则总页码数=整除结果,如果不能整除,则总页码数=整除结果+1
        int totalPage = (int)Math.ceil(totalCount*1.0/rows);
        pb.setTotalPage(totalPage);
        //计算每页开始的数据索引,获取每页显示的数据集合
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
        return pb;
    }
}
