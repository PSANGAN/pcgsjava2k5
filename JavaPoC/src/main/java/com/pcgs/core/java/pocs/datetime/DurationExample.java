package com.pcgs.core.java.pocs.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Instant;

public class DurationExample {
    public static void main(String[] args) {
        // Creating Duration
        Duration fiveHours = Duration.ofHours(5);
        Duration thirtyMinutes = Duration.ofMinutes(30);
        Duration tenSeconds = Duration.ofSeconds(10);
        Duration millis = Duration.ofMillis(5000);
        Duration nanos = Duration.ofNanos(1000000);

        // Complex duration
        Duration complex = Duration.ofHours(2).plusMinutes(30).plusSeconds(45);
        System.out.println("Complex: " + complex); // PT2H30M45S

        // Between two times
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 30);
        Duration workDay = Duration.between(start, end);
        System.out.println("Work day: " + workDay); // PT8H30M

        // Between LocalDateTime
        LocalDateTime meeting1 = LocalDateTime.of(2024, 1, 15, 10, 0);
        LocalDateTime meeting2 = LocalDateTime.of(2024, 1, 15, 14, 30);
        Duration meetingGap = Duration.between(meeting1, meeting2);
        System.out.println("Meeting gap: " + meetingGap); // PT4H30M

        // Between Instants
        Instant instant1 = Instant.now();
        // ... some operation ...
        Instant instant2 = Instant.now();
        Duration elapsed = Duration.between(instant1, instant2);

        // Parse from ISO-8601
        Duration parsed = Duration.parse("PT15H30M45S");
        System.out.println("Parsed: " + parsed);

        // Getting components
        long hours = complex.toHours(); // Total hours
        long minutes = complex.toMinutes(); // Total minutes
        long seconds = complex.getSeconds(); // Total seconds
        int nanosPart = complex.getNano(); // Nanoseconds part

        // More precise extraction
        long days = complex.toDays();
        long hoursOnly = complex.toHoursPart(); // Just the hours component
        int minutesOnly = complex.toMinutesPart(); // Just the minutes component
        int secondsOnly = complex.toSecondsPart(); // Just the seconds component

        // Arithmetic
        Duration doubled = complex.multipliedBy(2);
        Duration halved = complex.dividedBy(2);
        Duration added = complex.plus(Duration.ofHours(1));
        Duration subtracted = complex.minus(Duration.ofMinutes(15));
        Duration negated = complex.negated();
        Duration absolute = complex.abs();

        // Comparisons
        boolean isZero = complex.isZero();
        boolean isNegative = complex.isNegative();
        int comparison = complex.compareTo(fiveHours);

        // Practical example: Measuring execution time
        Instant startTime = Instant.now();

        // Simulate some work
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant endTime = Instant.now();
        Duration executionTime = Duration.between(startTime, endTime);
        System.out.println("Execution time: " + executionTime.toMillis() + " ms");

        // Format duration for display
        System.out.println(formatDuration(workDay));
    }

    // Helper method to format duration
    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
    }
}
