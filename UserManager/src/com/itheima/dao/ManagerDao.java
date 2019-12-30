package com.itheima.dao;

import com.itheima.domain.Manager;
import com.itheima.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ManagerDao {
    /**
     *
     * @param username 用户输入的帐号
     * @param password  用户输入的密码
     * @return Manager 将用户输入的封装为Manager对象
     */


    public Manager login(String username, String password){
        //创建JDBCTemplate对象,依赖DataSource
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        Manager manager = null;
        try {
            manager = template.queryForObject("select * from manager where username=? and password=?", new BeanPropertyRowMapper<Manager>(Manager.class), username, password);
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }
        return manager;
    }
}
