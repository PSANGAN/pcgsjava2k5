package com.pcgs.core.java.pocs.datetime;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        // Creating LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Now: " + now); // 2026-01-30T14:30:45.123

        LocalDateTime specific = LocalDateTime.of(2024, 12, 25, 10, 30);
        LocalDateTime withSeconds = LocalDateTime.of(2024, 12, 25, 10, 30, 45);
        LocalDateTime withNanos = LocalDateTime.of(2024, 12, 25, 10, 30, 45, 123000000);

        // Combining date and time
        LocalDate date = LocalDate.of(2024, 12, 25);
        LocalTime time = LocalTime.of(10, 30);
        LocalDateTime combined = LocalDateTime.of(date, time);

        // Parsing
        LocalDateTime parsed = LocalDateTime.parse("2024-12-25T10:30:45");
        System.out.println("Parsed: " + parsed);

        // Manipulating
        LocalDateTime tomorrow = now.plusDays(1);
        LocalDateTime nextWeek = now.plusWeeks(1);
        LocalDateTime nextHour = now.plusHours(1);
        LocalDateTime complex = now.plusDays(5).plusHours(3).plusMinutes(30);

        // Extracting components
        LocalDate dateOnly = now.toLocalDate();
        LocalTime timeOnly = now.toLocalTime();

        int year = now.getYear();
        Month month = now.getMonth();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();

        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatted = now.format(formatter);
        System.out.println("Formatted: " + formatted); // 30/01/2026 14:30:45

        // Custom formatting
        DateTimeFormatter custom = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy 'at' hh:mm a");
        String customFormatted = now.format(custom);
        System.out.println("Custom: " + customFormatted); // Friday, January 30, 2026 at 02:30 PM

        // Parsing with formatter
        String dateString = "25/12/2024 10:30:45";
        LocalDateTime parsedCustom = LocalDateTime.parse(dateString, formatter);
        System.out.println("Parsed custom: " + parsedCustom);

        // Comparisons
        boolean isBefore = specific.isBefore(now);
        boolean isAfter = specific.isAfter(now);

        // Calculate differences
        long daysBetween = ChronoUnit.DAYS.between(specific, now);
        long hoursBetween = ChronoUnit.HOURS.between(specific, now);
        long minutesBetween = ChronoUnit.MINUTES.between(specific, now);

        // Truncate
        LocalDateTime truncated = now.truncatedTo(ChronoUnit.HOURS);
        System.out.println("Truncated to hours: " + truncated);

        // With specific values
        LocalDateTime modified = now.withYear(2025)
                .withMonth(6)
                .withDayOfMonth(15)
                .withHour(9)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }
}
