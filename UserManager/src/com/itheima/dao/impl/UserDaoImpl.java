package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//JdbcTemplate操作数据库user表,添加或删除数据

public class UserDaoImpl implements UserDao {
    //创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 添加用户的方法
     * @param user
     */
    public void addUser(User user){
        //定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        //执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    /**
     *查询所有用户的方法
     * @return
     */
    public List<User> findAll() {
        return template.query("select * from user",new BeanPropertyRowMapper<User>(User.class));
    }

    /**
     *根据用户id删除用户数据的方法
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        template.update("delete from user where id=?",id);
    }

    /**
     * 根据用户id查询单个用户信息
     * @return
     */
    @Override
    public User findById(int id) {
        //定义sql
        String sql = "select * from user where id = ?";
        //执行sql
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
        return user;
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        //定义sql
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        //执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findTotalCount() {
        //定义sql
        String sql = "select count(*) from user";
        //执行sql
        int totalCount = template.queryForObject(sql, Integer.class);
        return  totalCount;
    }

    /**
     * 根据数据的索引和每页查询的行数,查询对应页面的数据集合
     * @param start 数据的开始索引
     * @param rows 每页显示的条目数
     * @return 查询到的页面的数据集合
     */
    @Override
    public List<User> findByPage(int start, int rows) {
        //定义sql
        String sql = "select * from user limit ?,?";
        //执行sql
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
        return list;
    }

    /**
     * 根据条件查询总记录数
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String> condition) {
        //这里由于无法判断条件查询的搜索框中传入录入哪些数据,所以要用到动态sql
        //定义sql
        String sql = "select count(*) from user where 1=1";
        //创建StringBuilder对象,拼接动态sql
        StringBuilder sb = new StringBuilder(sql);
        //创建List集合,用来存储sql语句中?的参数
        List<String> list = new ArrayList<String>();
        //遍历集合
        Set<Map.Entry<String, String>> entries = condition.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                list.add("%"+value+"%");
            }
        }
        sql=sb.toString();
        //执行sql
        int totalCount = template.queryForObject(sql, Integer.class,list.toArray());
        return  totalCount;
    }

    /**
     * 根据条件查询,获取分页显示的数据集合
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String> condition) {
        //这里由于无法判断条件查询的搜索框中传入录入哪些数据,所以要用到动态sql
        //定义sql
        String sql = "select * from user where 1=1";
        //创建StringBuilder对象,拼接动态sql
        StringBuilder sb = new StringBuilder(sql);
        //创建List集合,用来存储sql语句中?的参数
        List list = new ArrayList();
        //遍历集合
        Set<Map.Entry<String, String>> entries = condition.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                list.add("%"+value+"%");
            }
        }

        list.add(start);
        list.add(rows);
        //System.out.println(list);
        sb.append(" limit ?,?");
        //System.out.println(sb);
        sql=sb.toString();
        //执行sql
        List<User> UserList = template.query(sql, new BeanPropertyRowMapper<User>(User.class), list.toArray());
        return UserList;
    }
}
