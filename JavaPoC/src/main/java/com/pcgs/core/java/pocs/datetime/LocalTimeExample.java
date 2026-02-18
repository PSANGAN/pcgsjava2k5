package com.pcgs.core.java.pocs.datetime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalTimeExample {
    public static void main(String[] args) {
        // Creating LocalTime
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now); // 14:30:15.123456789

        LocalTime specificTime = LocalTime.of(14, 30); // 14:30:00
        LocalTime withSeconds = LocalTime.of(14, 30, 45); // 14:30:45
        LocalTime withNanos = LocalTime.of(14, 30, 45, 123456789);

        LocalTime parsed = LocalTime.parse("14:30:45");
        System.out.println("Parsed time: " + parsed);

        // Manipulating time
        LocalTime later = now.plusHours(2);
        LocalTime muchLater = now.plusMinutes(90);
        LocalTime wayLater = now.plusSeconds(3600);
        LocalTime inFuture = now.plusNanos(1000000);

        LocalTime earlier = now.minusHours(3);

        // Getting components
        int hour = now.getHour(); // 0-23
        int minute = now.getMinute(); // 0-59
        int second = now.getSecond(); // 0-59
        int nano = now.getNano(); // 0-999,999,999

        // Special times
        LocalTime midnight = LocalTime.MIDNIGHT; // 00:00:00
        LocalTime noon = LocalTime.NOON; // 12:00:00
        LocalTime min = LocalTime.MIN; // 00:00:00
        LocalTime max = LocalTime.MAX; // 23:59:59.999999999

        // Comparisons
        boolean isBefore = specificTime.isBefore(now);
        boolean isAfter = specificTime.isAfter(now);

        // Calculate difference
        long minutesBetween = ChronoUnit.MINUTES.between(specificTime, now);
        System.out.println("Minutes between: " + minutesBetween);

        // Truncate to remove precision
        LocalTime truncated = now.truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Truncated to minutes: " + truncated);
    }
}
