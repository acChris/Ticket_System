package com.weng.ticket.controller.admin;

import com.weng.ticket.service.TicketService;
import com.weng.ticket.util.GetSessionUtil;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.Result;
import com.weng.ticket.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: acChris
 * @Date: 2021/10/3 0:01
 * @Description:
 */
@Controller
@RequestMapping("/admin/search")
public class SearchController {

    @Autowired
    TicketService ticketService;

    @GetMapping("")
    public String search(HttpServletRequest request){
        request.setAttribute("path", "search");
        return "/admin/search";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result searchList(@RequestParam Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(ticketService.getTicketPage(pageUtil));
    }


}
