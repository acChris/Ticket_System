package com.weng.ticket.mapper;

import com.weng.ticket.entity.RentTicket;

public interface RentTicketMapper {
    RentTicket getRentTicketById(String id);

    int deleteByPrimaryKey(Integer id);

    int insert(RentTicket record);

    int insertSelective(RentTicket record);

    RentTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentTicket record);

    int updateByPrimaryKey(RentTicket record);

    int saveRentTicket(RentTicket rentTicket);

}