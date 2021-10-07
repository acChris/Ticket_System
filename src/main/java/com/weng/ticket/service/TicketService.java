package com.weng.ticket.service;

import com.weng.ticket.entity.Ticket;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @Author: acChris
 * @Date: 2021/10/3 11:45
 * @Description:
 */
@Service
public interface TicketService {

    Ticket selectByPrimaryKey(Integer id);
    
    /**
     * 根据分页分页查询 ticket 列表
     *
     * @param pageUtil
     * @return
     */
    PageResult getTicketPage(PageQueryUtil pageUtil);

    /**
     *  获取车票总数
     *
     * @return
     */
    int getTotalTickets();

    /**
     * 根据 车票 id 获取车票信息
     *
     * @param id
     * @return
     */
    int getTicketById(String id);

    /**
     * 保存车票
     * @param ticket
     * @return
     */
    String saveTicket(Ticket ticket);

    String insertTicketSelective(Ticket ticket);

    Boolean deleteTicketByIds(Integer[] ticketIds);
    
    Boolean updateTicketById(Ticket ticket);
}
