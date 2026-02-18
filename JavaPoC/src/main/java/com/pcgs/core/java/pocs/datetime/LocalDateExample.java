package com.pcgs.core.java.pocs.datetime;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class LocalDateExample {
    public static void main(String[] args) {
        // Creating LocalDate
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today); // 2026-01-30

        LocalDate specificDate = LocalDate.of(2024, 12, 25);
        System.out.println("Christmas 2024: " + specificDate); // 2024-12-25

        LocalDate withMonth = LocalDate.of(2024, Month.DECEMBER, 25);
        System.out.println("Using Month enum: " + withMonth);

        LocalDate parsed = LocalDate.parse("2024-06-15");
        System.out.println("Parsed date: " + parsed); // 2024-06-15

        // Manipulating dates
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);

        LocalDate lastWeek = today.minusWeeks(1);

        // Getting information
        int year = today.getYear();
        Month month = today.getMonth();
        int monthValue = today.getMonthValue(); // 1-12
        int day = today.getDayOfMonth();
        int dayOfYear = today.getDayOfYear(); // 1-365/366

        // Comparisons
        boolean isBefore = specificDate.isBefore(today);
        boolean isAfter = specificDate.isAfter(today);
        boolean isEqual = specificDate.isEqual(today);

        // Calculate difference
        long daysBetween = ChronoUnit.DAYS.between(specificDate, today);
        System.out.println("Days between: " + daysBetween);

        // Leap year check
        boolean isLeap = today.isLeapYear();
        System.out.println("Is leap year: " + isLeap);

        // Length of month
        int daysInMonth = today.lengthOfMonth();
        System.out.println("Days in month: " + daysInMonth);
    }
}
