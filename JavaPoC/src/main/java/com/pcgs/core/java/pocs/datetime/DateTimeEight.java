package com.pcgs.core.java.pocs.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class DateTimeEight {
    public static void main(String[] args) {

//        LocalDate today = LocalDate.now();
//        System.out.println(today);
//
//        LocalDate tomorrow = LocalDate.of(2000,12,21);
//        System.out.println(tomorrow);
//
//        LocalDate past = LocalDate.parse("1999-09-19");
//        System.out.println(past);
//
//        System.out.println(tomorrow.getYear());
//        System.out.println(tomorrow.get(ChronoField.DAY_OF_YEAR));

//        LocalTime now = LocalTime.now();
//        System.out.println(now);
//
//        LocalTime past = LocalTime.of(12,59,59);
//        System.out.println(past);
//
//        LocalTime future = LocalTime.parse("13:59:59");
//        System.out.println(future);
//        System.out.println(future.getHour());


        LocalDateTime dateTime  = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDateTime past = LocalDateTime.of(2000,12,21,12,59,59);
        System.out.println(past.toLocalDate());
        System.out.println(past.toLocalTime());
    }

}
