package com.weng.ticket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: acChris
 * @Date: 2021/10/3 0:02
 * @Description:
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @GetMapping("")
    public String user(HttpServletRequest request){
        request.setAttribute("path", "user");
        return "/admin/users";
    }


}
