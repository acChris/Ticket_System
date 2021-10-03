package com.weng.ticket.entity;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private Integer id;

    private String ticketFrom;

    private String ticketTo;

    private String ticketPayer;

    private Byte ticketCount;

    private Date startTime;

    private Date endTime;

    private Date buyTime;

    private Date payTime;

    private Byte ticketStatus;

    private Byte isDeleted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketFrom() {
        return ticketFrom;
    }

    public void setTicketFrom(String ticketFrom) {
        this.ticketFrom = ticketFrom;
    }

    public String getTicketTo() {
        return ticketTo;
    }

    public void setTicketTo(String ticketTo) {
        this.ticketTo = ticketTo;
    }

    public String getTicketPayer() {
        return ticketPayer;
    }

    public void setTicketPayer(String ticketPayer) {
        this.ticketPayer = ticketPayer;
    }

    public Byte getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Byte ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Byte ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ticketFrom=").append(ticketFrom);
        sb.append(", ticketTo=").append(ticketTo);
        sb.append(", ticketPayer=").append(ticketPayer);
        sb.append(", ticketCount=").append(ticketCount);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", ticketStatus=").append(ticketStatus);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}