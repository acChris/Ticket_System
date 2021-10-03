package com.weng.ticket.service;

import com.weng.ticket.entity.RentTicket;
import org.springframework.stereotype.Service;

/**
 * @Author: acChris
 * @Date: 2021/10/3 15:49
 * @Description:
 */
@Service
public interface RentTicketService {

    String saveRentTicket(RentTicket rentTicket);

    /**
     * 根据 余票id 获取余票信息
     *
     * @param id
     * @return
     */
    RentTicket getRentTicketById(Integer id);
}
