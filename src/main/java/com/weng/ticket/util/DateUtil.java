package com.weng.ticket.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: acChris
 * @Date: 2021/10/5 15:54
 * @Description:
 */
public class DateUtil {
    /**
     * Bootstrap 的 datetime-local，转换为 Date 型
     * 2021-10-05T15:53 => 2021-10-05 15:53:00
     */
    public static Date StringToDate(String inputDate) throws ParseException {
        if (inputDate.contains("T")){
            inputDate = inputDate.replace("T", " ") + ":00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = sdf.parse(inputDate);
        System.out.println(parseDate);
        return parseDate;
    }
}
