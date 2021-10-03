package com.weng.ticket;

import com.weng.ticket.entity.AdminUser;
import com.weng.ticket.service.AdminUserService;
import com.weng.ticket.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: acChris
 * @Date: 2021/10/1 21:36
 * @Description:
 */
@SpringBootTest
public class MyBatisTests {

    @Autowired
    AdminUserService userService;


    @Test
    public void login(){
        AdminUser adminUser = userService.login("admin", "admin");
        if (adminUser != null){
            System.out.println(adminUser.getLoginUserName() + " " + adminUser.getLoginPassword() );
        }
    }

    @Test
    public void insert(){
        AdminUser adminUser = new AdminUser("test", MD5Util.MD5Encode("test", "UTF8"), "test");
        Integer i = userService.insertAdminUser(adminUser);
        if (i != 0){
            System.out.println("insert success");
        }
    }

    @Test
    public void updatePassword(){
        AdminUser userDetailById = userService.getUserDetailById(4);
        System.out.println(userDetailById.getLoginPassword());
        userService.updatePassword(userDetailById.getAdminUserId(), userDetailById.getLoginUserName(), userDetailById.getLoginPassword());
        System.out.println(userService.getUserDetailById(4).getLoginPassword());
    }

    @Test
    public void deleteUserById()  {
        AdminUser userDetailById = userService.getUserDetailById(4);
        Boolean isDeleted = userService.deleteUserById(userDetailById.getAdminUserId());
        if (isDeleted){
            System.out.println("delete success");
        }
    }
}
