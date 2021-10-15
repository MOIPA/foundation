package com.company.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author tr
 * @date 2020/6/22 21:20
 */
public class main_test {

    public static void main(String[] args) {
        Liner_search a = new Liner_search();

        // 初始化数据
        Data data = Data.getInstance();
        data.setArray(new int[]{1,2,3,4,5,6});

        int b = a.linerSearch(3);
        System.out.println(b);

        LocalDateTime localDateTime = Instant.ofEpochSecond(1619545560).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = localDateTime.format(formatter);
        System.out.println(time);
        System.out.println(LocalDateTime.of(2020,3,11,0,0).toInstant(ZoneOffset.ofHours(8)).toEpochMilli());

        System.out.println("time util");
        System.out.println(Util.isDateInInterval(new Date(),new Date(2021-1900,7-1,10),new Date(2021-1900,7-1,14)));

    }
}
