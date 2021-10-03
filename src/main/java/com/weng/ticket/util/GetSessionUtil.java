package com.weng.ticket.util;

import com.weng.ticket.entity.AdminUser;
import com.weng.ticket.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: acChris
 * @Date: 2021/10/3 0:05
 * @Description:
 */

public class GetSessionUtil {

    @Autowired
    private static AdminUserService adminUserService;

    public static boolean getSession(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return true;
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return false;
    }
}
