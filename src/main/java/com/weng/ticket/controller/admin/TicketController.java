package com.weng.ticket.controller.admin;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.entity.Ticket;
import com.weng.ticket.service.TicketService;
import com.weng.ticket.util.DateUtil;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.Result;
import com.weng.ticket.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: acChris
 * @Date: 2021/10/3 0:01
 * @Description:
 */
@Controller
@RequestMapping("/admin/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("")
    public String ticketSearch(HttpServletRequest request){
        request.setAttribute("path", "ticket");
        return "/admin/ticket";
    }

    @GetMapping("/list")
    @ResponseBody
    public Result ticketList(@RequestParam Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(ticketService.getTicketPage(pageUtil));
    }

    @PostMapping("/save")
    @ResponseBody
    public Result saveTicket(@RequestParam("ticketId")      Integer ticketId,
                             @RequestParam("ticketFrom")    String ticketFrom,
                             @RequestParam("ticketTo")      String ticketTo,
                             @RequestParam("ticketPayer")   String ticketPayer,
                             @RequestParam("ticketCount")   Byte ticketCount,
                             @RequestParam("startTime")     String startTime,
                             @RequestParam("endTime")       String endTime) throws ParseException {
        Date sTime = DateUtil.StringToDate(startTime);
        Date eTime = DateUtil.StringToDate(endTime);
        Ticket ticket = new Ticket(ticketId, ticketFrom, ticketTo, ticketPayer, ticketCount, sTime, eTime, null);
        String saveTicketResult = ticketService.saveTicket(ticket);
        if ("SUCCESS".equals(saveTicketResult)){
            return ResultGenerator.genSuccessResult("添加成功!");
        }else{
            return ResultGenerator.genFailResult(saveTicketResult);
        }
    }
    
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestParam Ticket ticket){
        Boolean isSuccess = ticketService.updateTicketById(ticket);
        if (isSuccess){
            return ResultGenerator.genSuccessResult("添加成功!");
        }else{
            return ResultGenerator.genFailResult("添加失败，请重试！");
        }
    }
    
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        Boolean saveTicketResult = ticketService.deleteTicketByIds(ids);
        if (saveTicketResult){
            return ResultGenerator.genSuccessResult("添加成功!");
        }else{
            return ResultGenerator.genFailResult("添加失败，请重试！");
        }
    }
}
