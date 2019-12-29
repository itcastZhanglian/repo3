package com.itheima.test;

import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void test1(){
        User user = new User();
        user.setName("cxk");
        user.setAge(Integer.parseInt("18"));
        user.setGender("nan");
        user.setAddress("上海");
        user.setQq("12345678");
        user.setEmail("12345678@qq.com");

        System.out.println(user);
        new UserDaoImpl().addUser(user);
    }
}
