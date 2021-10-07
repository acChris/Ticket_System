package com.weng.ticket.mapper;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.PageResult;

import java.util.List;

public interface RentTicketMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RentTicket record);

    int insertSelective(RentTicket record);

    RentTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentTicket record);

    int updateByPrimaryKey(RentTicket record);

    /**
     * 获取RentTicketList列表
     * @param pageUtil
     * @return
     */
    List<RentTicket> getRentTicketList(PageQueryUtil pageUtil);

    int getTotalRentTickets(PageQueryUtil pageUtil);

    RentTicket saveRentTicket(RentTicket rentTicket);

    int deleteBatch(Integer[] ids);

    List<RentTicket> selectByTicketFromOrTo(String keyword);

    int selectByTicketFromOrToCount(String keyword);

    int subRentTicket(RentTicket rentTicket);
}