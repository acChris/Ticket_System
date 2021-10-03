package com.weng.ticket.service.impl;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.mapper.RentTicketMapper;
import com.weng.ticket.service.RentTicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: acChris
 * @Date: 2021/10/3 15:49
 * @Description:
 */
@Service
public class RentTicketServiceImpl implements RentTicketService {

    @Resource
    RentTicketMapper rentTicketMapper;

    /**
     *
     * @param rentTicket
     * @return
     */
    @Override
    public String saveRentTicket(RentTicket rentTicket) {
        int saveResultCount = rentTicketMapper.saveRentTicket(rentTicket);
        if (saveResultCount == 0) {
            return "保存车票失败";
        }
        return "success";
    }

    @Override
    public RentTicket getRentTicketById(Integer id) {
        return rentTicketMapper.selectByPrimaryKey(id);
    }
}
