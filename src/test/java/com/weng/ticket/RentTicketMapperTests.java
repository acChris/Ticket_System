package com.weng.ticket;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.mapper.RentTicketMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @Author: acChris
 * @Date: 2021/10/3 17:10
 * @Description:
 */
@SpringBootTest
public class RentTicketMapperTests {

    @Resource
    RentTicketMapper rentTicketMapper;

    @Test
    public void save(){
        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(formatter.format(date));
        RentTicket rentTicket = new RentTicket(null, "testFrom","testTo",date,null,100,null);
        int save = rentTicketMapper.saveRentTicket(rentTicket);
        if (save != 0){
            System.out.println("插入成功！");
        }else{
            System.out.println("插入失败！");
        }
    }
}
