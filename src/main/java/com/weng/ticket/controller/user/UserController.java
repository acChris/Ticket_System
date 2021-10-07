package com.weng.ticket.controller.user;

import com.weng.ticket.entity.AdminUser;
import com.weng.ticket.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: acChris
 * @Date: 2021/10/3 0:02
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    AdminUserService adminUserService;
    
    @GetMapping("")
    public String user(HttpServletRequest request){
        request.setAttribute("path", "user");
        return "/user/ticket";
    }
    
    /*
     *   显示基本信息
     *
     *   步骤：
     *       1. 获取登录用户ID
     *       2. 根据ID获取用户信息
     *       3. 若用户为空，返回登录页面
     *       4. 若用户不为空，HTTP请求 设置路径为profile，设置登录用户名 和 昵称。
     *       5. 返回当前页
     * */
    
    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        System.out.println("成功显示");
        return "user/profile";
    }
    
    
    /*
     *   修改密码
     *
     *   步骤：
     *       1. 判参数是否有空值
     *       2. 获取登录用户ID
     *       3. 更新密码
     *           1. 若成功，获取session移除登录用户ID，登录用户，错误信息
     *           2. 若失败，返回 修改错误
     * */
    
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "修改失败";
        }
    }
    
    /*
     *   修改基础信息
     *
     *   @RequestParam：登录用户名，昵称
     *
     *   步骤：
     *       1. 判输入是否有一个为空
     *       2. 根据请求中的 session 获取登录用户ID
     *       3. 更新用户名和昵称
     *           1. 若成功返回 success
     *           2. 若失败返回 修改失败
     * */
    
    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request,
                             @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return "success";
        } else {
            return "修改失败";
        }
    }
    
    
}
