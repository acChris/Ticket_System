package com.weng.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.weng.ticket.mapper")
public class TicketSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketSystemApplication.class, args);
    }
}
