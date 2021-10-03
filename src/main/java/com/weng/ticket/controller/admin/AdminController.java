package com.weng.ticket.controller.admin;

import com.weng.ticket.entity.AdminUser;
import com.weng.ticket.service.*;
import com.weng.ticket.util.GetSessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller     
public class AdminController {

    @Resource
    private AdminUserService adminUserService;


    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }

    /*
    *   后台界面主页 / Dashboard
    *
    *   通过 HTTP 服务请求给六个模块进行属性设置:
    *       1. 设置路径为 index
    *       2. 返回后台主页
    * */

    @GetMapping({"", "/", "/index", "/admin/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }

    /*
    *   登录界面
    *
    *   @RequestParam：用户名，密码，验证码
    *
    *   步骤：
    *       1. 判断验证码是否为空
    *       2. 判断用户和密码其一是否为空
    *       3. 判断验证码是否正确
    *       4. 判断用户是否存在
    *           1. 若存在，  则 session 设置属性 用户和用户ID
    *           2. 若不存在，则 session 设置属性 "errormsg" 为 "登录失败" ，返回登录界面
    * */

    @PostMapping(value = "/admin/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        }
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

    @GetMapping("/admin/profile")
    public String profile(HttpServletRequest request) {
        if (GetSessionUtil.getSession(request)) return "admin/login";
        return "admin/profile";
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

    @PostMapping("/admin/profile/password")
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

    @PostMapping("/admin/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
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

    @GetMapping("/admin/logout")
    public String logout(HttpServletRequest request) {
        /*
        * 系统管理-安全退出
        *
        * 登出所需操作：
        *   1. 移除 Session 中的登录用户ID 属性
        *   2. 移除 Session 的登录用户     属性
        *   3. 移除 Session 的错误页      属性
        *   4. 返回登录页
        * */
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
