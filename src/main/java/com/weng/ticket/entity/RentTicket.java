package com.weng.ticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class RentTicket implements Serializable {
    private Integer id;

    private String rentTicketFrom;

    private String rentTicketTo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    private Integer rentTicketCount;

    private Byte rentTicketStatus;

    private Byte isDeleted;

    private static final long serialVersionUID = 1L;


    public RentTicket(Integer id, String rentTicketFrom, String rentTicketTo, Date startTime, Date endTime, Integer rentTicketCount, Byte rentTicketStatus, Byte isDeleted) {
        this.id = id;
        this.rentTicketFrom = rentTicketFrom;
        this.rentTicketTo = rentTicketTo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rentTicketCount = rentTicketCount;
        this.rentTicketStatus = rentTicketStatus;
        this.isDeleted = isDeleted;
    }

    public RentTicket(Integer rentTicketId, String rentTicketFrom,
                      String rentTicketTo, Date sTime, Date eTime, Integer rentTicketCount) {
        this.id = rentTicketId;
        this.rentTicketFrom = rentTicketFrom;
        this.rentTicketTo = rentTicketTo;
        this.startTime = sTime;
        this.endTime = eTime;
        this.rentTicketCount = rentTicketCount;
    }
    
    public RentTicket(Integer rentTicketId, String rentTicketFrom, String rentTicketTo, Date sTime, Date eTime) {
        this.id = rentTicketId;
        this.rentTicketFrom = rentTicketFrom;
        this.rentTicketTo = rentTicketTo;
        this.startTime = sTime;
        this.endTime = eTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRentTicketFrom() {
        return rentTicketFrom;
    }

    public void setRentTicketFrom(String rentTicketFrom) {
        this.rentTicketFrom = rentTicketFrom;
    }

    public String getRentTicketTo() {
        return rentTicketTo;
    }

    public void setRentTicketTo(String rentTicketTo) {
        this.rentTicketTo = rentTicketTo;
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

    public Integer getRentTicketCount() {
        return rentTicketCount;
    }

    public void setRentTicketCount(Integer rentTicketCount) {
        this.rentTicketCount = rentTicketCount;
    }

    public Byte getRentTicketStatus() {
        return rentTicketStatus;
    }

    public void setRentTicketStatus(Byte rentTicketStatus) {
        this.rentTicketStatus = rentTicketStatus;
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
        sb.append(", rentTicketFrom=").append(rentTicketFrom);
        sb.append(", rentTicketTo=").append(rentTicketTo);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", rentTicketCount=").append(rentTicketCount);
        sb.append(", rentTicketStatus=").append(rentTicketStatus);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}