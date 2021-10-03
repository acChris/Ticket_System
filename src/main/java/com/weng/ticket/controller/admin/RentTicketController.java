package com.weng.ticket.controller.admin;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.service.RentTicketService;
import com.weng.ticket.service.TicketService;
import com.weng.ticket.util.GetSessionUtil;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.Result;
import com.weng.ticket.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: acChris
 * @Date: 2021/10/1 21:31
 * @Description:
 */
@Controller
@RequestMapping("/admin/ticket")
public class RentTicketController {

    @Resource
    TicketService ticketService;
    @Resource
    RentTicketService rentTicketService;

    @GetMapping("")
    public String ticket(HttpServletRequest request){
        request.setAttribute("path", "ticket");
        return "admin/ticket";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        // 判参数的 page 和 limit 是否为空
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！");
        }
        // 根据参数 map 分页查询
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(ticketService.getTicketPage(pageUtil));
    }

    @GetMapping("/add")
    public String edit(){
        return "/admin/ticket/add";
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestParam RentTicket rentTicket){
        String saveRentTicketResult = rentTicketService.saveRentTicket(rentTicket);
        if ("success".equals(saveRentTicketResult)){
            return ResultGenerator.genSuccessResult("添加成功!");
        }else{
            return ResultGenerator.genFailResult(saveRentTicketResult);
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(HttpServletRequest request, @PathVariable Integer id){
        request.setAttribute("path", "edit");
        RentTicket rentTicket = rentTicketService.getRentTicketById(id);
        if (rentTicket == null) {
            return "error/error_400";
        }
        return "/admin/ticket";
    }
    


}
