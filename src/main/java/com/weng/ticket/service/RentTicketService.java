package com.weng.ticket.service;

import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.PageResult;
import org.apache.ibatis.annotations.Param;
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

    PageResult getRentTicketPage(PageQueryUtil pageUtil);

    RentTicket selectByPrimaryKey(Integer rentTicketId);

    String updateRentTicketById(RentTicket rentTicket);

    Boolean deleteBatch(Integer[] ids);

    PageResult selectByTicketFromOrTo(String keyword);

    Integer selectByTicketFromOrToCount(String keyword);

    String subRentTicket(@Param("rentTicket") RentTicket rentTicket);
    
}
