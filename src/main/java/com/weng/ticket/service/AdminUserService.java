package com.weng.ticket.service;

import com.weng.ticket.entity.AdminUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminUserService {

    AdminUser login(String userName, String password);

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    Integer insertAdminUser(AdminUser user);

    List<AdminUser> getAllAdminUsers();

    /**
     * 获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改当前登录用户的名称信息
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

    /**
     * 根据 ID 删除用户
     * @param adminUserId
     */
    Boolean deleteUserById(Integer adminUserId);
}
