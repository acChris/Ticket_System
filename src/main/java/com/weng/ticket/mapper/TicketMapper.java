package com.weng.ticket.mapper;

import com.weng.ticket.entity.Ticket;
import com.weng.ticket.util.PageQueryUtil;

import java.util.List;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    List<Ticket> findTicketList(PageQueryUtil pageQueryUtil);

    int getTotalTickets(PageQueryUtil pageUtil);
}