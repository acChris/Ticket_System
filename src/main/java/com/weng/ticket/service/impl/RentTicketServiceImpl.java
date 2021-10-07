package com.weng.ticket.service.impl;

import com.github.pagehelper.Page;
import com.weng.ticket.entity.RentTicket;
import com.weng.ticket.mapper.RentTicketMapper;
import com.weng.ticket.service.RentTicketService;
import com.weng.ticket.util.PageQueryUtil;
import com.weng.ticket.util.PageResult;
import com.weng.ticket.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
        RentTicket rentTicket1 = rentTicketMapper.selectByPrimaryKey(rentTicket.getId());
        int saveResult = -1;
        if (rentTicket1 == null){
            saveResult  = rentTicketMapper.insertSelective(rentTicket);
        }else{
            saveResult = rentTicketMapper.updateByPrimaryKeySelective(rentTicket);
        }
        return ResultGenerator.genResult(saveResult);
    }

    @Override
    public RentTicket getRentTicketById(Integer id) {
        return rentTicketMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult getRentTicketPage(PageQueryUtil pageUtil) {
        List<RentTicket> rentTicketList = rentTicketMapper.getRentTicketList(pageUtil);
        int totalRentTickets = rentTicketMapper.getTotalRentTickets(null);
        PageResult pageResult = new PageResult(rentTicketList, totalRentTickets, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public RentTicket selectByPrimaryKey(Integer rentTicketId) {
        return rentTicketMapper.selectByPrimaryKey(rentTicketId);
    }

    @Override
    public String updateRentTicketById(RentTicket rentTicket) {
        return ResultGenerator.genResult(rentTicketMapper.updateByPrimaryKey(rentTicket));
    }


    /**
     * 开启事务，方便反复连接数据库 和 回滚
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1){
            return false;
        }
        int result = rentTicketMapper.deleteBatch(ids);
        return result > 0;
    }

    @Override
    public PageResult selectByTicketFromOrTo(String keyword) {
        Integer count = selectByTicketFromOrToCount(keyword);
        List<RentTicket> rentTickets = rentTicketMapper.selectByTicketFromOrTo(keyword);
        return new PageResult(rentTickets,count, 0, 10);
    }

    @Override
    public Integer selectByTicketFromOrToCount(String keyword) {
        return rentTicketMapper.selectByTicketFromOrToCount(keyword);
    }

    @Override
    public String subRentTicket(RentTicket rentTicket) {
        return ResultGenerator.genResult(rentTicketMapper.subRentTicket(rentTicket));
    }
}
