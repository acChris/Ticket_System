package com.weng.ticket.service.impl;

import com.weng.ticket.entity.Ticket;
import com.weng.ticket.mapper.TicketMapper;
import com.weng.ticket.service.TicketService;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.PageResult;
import com.weng.ticket.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: acChris
 * @Date: 2021/10/3 11:45
 * @Description:
 */

@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    TicketMapper ticketMapper;
    
    @Override
    public Ticket selectByPrimaryKey(Integer id) {
        return ticketMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public PageResult getTicketPage(PageQueryUtil pageUtil) {
        // 根据分页查询查出ticket列表
        List<Ticket> ticketPage = ticketMapper.findTicketList(pageUtil);
        // 根据分页查询记录总数
        int totalTickets = ticketMapper.getTotalTickets(null);
        // 生成分页结果（ticket列表，总数，limit，page）
        return new PageResult(ticketPage, totalTickets, pageUtil.getLimit(), pageUtil.getPage());
    }


    @Override
    public int getTotalTickets() {
        return ticketMapper.getTotalTickets(null);
    }

    @Override
    public int getTicketById(String id) {
        return 0;
    }

    @Override
    @Transactional
    public String saveTicket(Ticket ticket) {
        return ResultGenerator.genResult(ticketMapper.insertSelective(ticket));
    }

    @Override
    public String insertTicketSelective(Ticket ticket) {
        return ResultGenerator.genResult(ticketMapper.insertSelective(ticket));
    }
    
    @Override
    public Boolean updateTicketById(Ticket ticket) {
        return ticketMapper.updateByPrimaryKeySelective(ticket) > 0;
    }
    
    @Override
    @Transactional
    public Boolean deleteTicketByIds(Integer[] ticketIds) {
        return ticketMapper.deleteBatchByIds(ticketIds) > 0;
    }
}
