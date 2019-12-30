package com.itheima.test;

import com.itheima.dao.ManagerDao;
import com.itheima.domain.Manager;
import org.junit.Test;

public class ManagerDaoTest {
    @Test
    public void test1(){
        String username = "zhangsan";
        String password = "123";
        ManagerDao managerDao = new ManagerDao();
        Manager manager = managerDao.login(username, password);
        System.out.println(manager);
    }
}
