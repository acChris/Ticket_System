package com.weng.ticket.controller.admin;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.service.RentTicketService;

import com.weng.ticket.util.DateUtil;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.Result;
import com.weng.ticket.util.ResultGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: acChris
 * @Date: 2021/10/1 21:31
 * @Description:
 */
@Controller
@RequestMapping("/admin/rentTicket")
public class RentTicketController {

    @Resource
    RentTicketService rentTicketService;

    @GetMapping("")
    public String ticket(HttpServletRequest request){
        request.setAttribute("path", "rentTicket");
        return "admin/rentTicket";
    }


    @GetMapping("/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        // 判参数的 page 和 limit 是否为空
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！");
        }

        // 查看输入键值对
        /*for (String key : params.keySet()){
            System.out.println(key + ": " + params.get(key));
        }*/

        // 根据参数 map 分页查询
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(rentTicketService.getRentTicketPage(pageUtil));
    }

    @GetMapping("/add")
    public String edit(){
        return "/admin/rentTicket/add";
    }


    @PostMapping("/save")
    @ResponseBody
    public Result save(
                       @RequestParam("rentTicketFrom") String rentTicketFrom,
                       @RequestParam("rentTicketTo") String rentTicketTo,
                       @RequestParam("startTime") String startTime,
                       @RequestParam("endTime") String endTime,
                       @RequestParam("rentTicketCount") Integer rentTicketCount) throws ParseException {
        Date sTime = DateUtil.StringToDate(startTime);
        Date eTime = DateUtil.StringToDate(endTime);
        RentTicket rentTicket = new RentTicket(null, rentTicketFrom, rentTicketTo, sTime, eTime, rentTicketCount, null, null);
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
    
    
    @PostMapping("/delete")
    @ResponseBody
    public Result deleteRentTicket(@RequestBody Integer[] ids){
        if (ids.length < 1){
            return ResultGenerator.genFailResult("参数异常");
        }

        /*for (Integer i : ids){
            System.out.print(i + "  ");
        }
        System.out.println();*/

        Boolean isSuccess = rentTicketService.deleteBatch(ids);
        if (isSuccess){
            return ResultGenerator.genSuccessResult("删除成功!");
        }else{
            return ResultGenerator.genFailResult("删除失败！");
        }
    }
    
    
    @PostMapping("/edit/{rentTicketId}")
    @ResponseBody
    public Result edit(@PathVariable("rentTicketId")    Integer rentTicketId,
                       @RequestParam("rentTicketFrom")  String  rentTicketFrom,
                       @RequestParam("rentTicketTo")    String  rentTicketTo,
                       @RequestParam("startTime")       String  startTime,
                       @RequestParam("endTime")         String endTime,
                       @RequestParam("rentTicketCount") Integer rentTicketCount) throws ParseException {
        // 将 Time 转为 Date 类型
        Date sTime = DateUtil.StringToDate(startTime);
        Date eTime = DateUtil.StringToDate(endTime);
        
        RentTicket rentTicket = new RentTicket(rentTicketId, rentTicketFrom, rentTicketTo, sTime, eTime, rentTicketCount);

//        System.out.println("经过edit方法。。。");
//        System.out.println(rentTicket);
        
        String saveRentTicketResult = rentTicketService.updateRentTicketById(rentTicket);
        if ("SUCCESS".equals(saveRentTicketResult)) {
            return ResultGenerator.genSuccessResult(rentTicket);
        }else{
            return ResultGenerator.genFailResult("修改余票失败，请重试！");
        }
    }
}
