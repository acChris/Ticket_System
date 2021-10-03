package com.weng.ticket;

import com.weng.ticket.entity.Ticket;
import com.weng.ticket.mapper.TicketMapper;
import com.weng.ticket.util.PageQueryUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: acChris
 * @Date: 2021/10/3 17:40
 * @Description:
 */
@SpringBootTest
public class TicketMapperTests {

    @Autowired
    TicketMapper ticketMapper;

    @Test
    public void findTicketList(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("page", 1);
        map.put("limit", 10);
        List<Ticket> ticketList = ticketMapper.findTicketList(new PageQueryUtil(map));
        for (Ticket ticket : ticketList){
            System.out.println(ticket);
        }
    }
}
