package com.weng.ticket.controller.user;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.service.RentTicketService;
import com.weng.ticket.service.TicketService;
import com.weng.ticket.util.DateUtil;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.Result;
import com.weng.ticket.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: acChris
 * @Date: 2021/10/1 21:31
 * @Description:
 */
@Controller
@RequestMapping("/user/rentTicket")
public class SearchRentTicketController {

    @Resource
    RentTicketService rentTicketService;

    @GetMapping("")
    public String ticket(HttpServletRequest request){
        request.setAttribute("path", "rentTicket");
        return "user/rentTicket";
    }


    @GetMapping("/list")
    @ResponseBody
    public Result list(HttpServletRequest request, @RequestParam Map<String, Object> params){
        // 判参数的 page 和 limit 是否为空
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！");
        }

        // 查看输入键值对
        /*for (String key : params.keySet()){
            System.out.println(key + ": " + params.get(key));
        }*/

        // 根据参数 map 分页查询
        String loginUserName = request.getSession().getAttribute("loginUserName").toString();
        params.put("loginUserName", loginUserName);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(rentTicketService.getRentTicketPage(pageUtil));
    }
    
    /**
     * 用户订票
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addTicket(@RequestParam("rentTicketId")    Integer rentTicketId,
                            @RequestParam("rentTicketFrom")  String  rentTicketFrom,
                            @RequestParam("rentTicketTo")    String  rentTicketTo,
                            @RequestParam("startTime")       String  startTime,
                            @RequestParam("endTime")         String endTime )throws ParseException {
        Date sTime = DateUtil.StringToDate(startTime);
        Date eTime = DateUtil.StringToDate(endTime);
        RentTicket rentTicket = new RentTicket(rentTicketId, rentTicketFrom, rentTicketTo, sTime, eTime);
        System.out.println(sTime);
        System.out.println(eTime);
        String result = rentTicketService.subRentTicket(rentTicket);
        if ("SUCCESS".equals(result)){
            return ResultGenerator.genSuccessResult("订票成功！");
        }else{
            return ResultGenerator.genFailResult("订票失败，请重试！");
        }
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestParam("id") Integer id,
                       @RequestParam("rentTicketFrom") String rentTicketFrom,
                       @RequestParam("rentTicketTo") String rentTicketTo,
                       @RequestParam("startTime") String startTime,
                       @RequestParam("endTime") String endTime,
                       @RequestParam("rentTicketCount") Integer rentTicketCount) throws ParseException {
        Date sTime = DateUtil.StringToDate(startTime);
        Date eTime = DateUtil.StringToDate(endTime);
        RentTicket rentTicket = new RentTicket(id, rentTicketFrom, rentTicketTo, sTime, eTime, rentTicketCount, null, null);
        String saveRentTicketResult = rentTicketService.saveRentTicket(rentTicket);
        if ("SUCCESS".equals(saveRentTicketResult)){
            return ResultGenerator.genSuccessResult("添加成功!");
        }else{
            return ResultGenerator.genFailResult(saveRentTicketResult);
        }
    }

    @GetMapping("/edit/{rentTicketId}")
    @ResponseBody
    public Result editRentTicketId(@PathVariable("rentTicketId") Integer rentTicketId){
        RentTicket rentTicket = rentTicketService.selectByPrimaryKey(rentTicketId);
        return ResultGenerator.genSuccessResult(rentTicket);
    }

}
