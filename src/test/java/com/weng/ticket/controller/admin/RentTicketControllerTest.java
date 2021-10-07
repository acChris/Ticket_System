package com.weng.ticket.controller.admin;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.service.RentTicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: acChris
 * @Date: 2021/10/5 12:53
 * @Description:
 */
@SpringBootTest
class RentTicketControllerTest {

    @Autowired
    RentTicketService rentTicketService;

    @Test
    void save() {
        RentTicket rentTicket = new RentTicket(null,"test1","test2",new Date(),new Date(), 1,null,null);
        String s = rentTicketService.saveRentTicket(rentTicket);
        if ("success".equals(s)) {
            System.out.println("RentTicket save success!");
        }
    }

    @Test
    void edit() {
    }

    @Test
    void deleteRentTicket() {
    }
}