package com.weng.ticket.service.impl;

import com.weng.ticket.mapper.AdminUserMapper;
import com.weng.ticket.entity.AdminUser;
import com.weng.ticket.service.AdminUserService;
import com.weng.ticket.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;


    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
//        密码忘记解决方案：输入123456后，在数据库把加密后的值修改为 passwordMd5 即可
//        System.out.println(passwordMd5);
        return adminUserMapper.login(userName, passwordMd5);
    }

    @Override
    public List<AdminUser> getAllAdminUsers() {
        return adminUserMapper.getAllAdminUsers();
    }

    @Override
    public Integer insertAdminUser(AdminUser user) {
        return adminUserMapper.insertAdminUser(user);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //修改信息
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteUserById(Integer adminUserId) {
        return adminUserMapper.deleteUserById(adminUserId) != 0;
    }
}
