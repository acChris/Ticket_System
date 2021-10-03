package com.weng.ticket.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: acChris
 * @Date: 2021/10/1 16:29
 * @Description:
 */
@Configuration
@MapperScan("com.weng.ticket.mapper")
public class MyBatisConfig {


}
