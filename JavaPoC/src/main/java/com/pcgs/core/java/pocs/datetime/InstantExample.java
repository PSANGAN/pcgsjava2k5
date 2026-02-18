package com.pcgs.core.java.pocs.datetime;

import java.time.Instant;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class InstantExample {
    public static void main(String[] args) {
        // Creating Instant
        Instant now = Instant.now();
        System.out.println("Now (UTC): " + now); // 2026-01-30T19:30:45.123Z

        // Epoch time (January 1, 1970, 00:00:00 UTC)
        Instant epoch = Instant.EPOCH;
        System.out.println("Epoch: " + epoch); // 1970-01-01T00:00:00Z

        // From epoch seconds
        Instant fromSeconds = Instant.ofEpochSecond(1640000000);
        System.out.println("From epoch seconds: " + fromSeconds);

        // From epoch milliseconds
        Instant fromMillis = Instant.ofEpochMilli(1640000000000L);
        System.out.println("From epoch millis: " + fromMillis);

        // Parse from ISO-8601
        Instant parsed = Instant.parse("2024-12-25T10:30:45.123Z");
        System.out.println("Parsed: " + parsed);

        // Getting epoch values
        long epochSecond = now.getEpochSecond();
        int nanoAdjustment = now.getNano();
        long epochMilli = now.toEpochMilli();

        System.out.println("Epoch seconds: " + epochSecond);
        System.out.println("Epoch millis: " + epochMilli);

        // Manipulating Instant
        Instant later = now.plusSeconds(3600);
        Instant muchLater = now.plus(Duration.ofHours(24));
        Instant earlier = now.minusSeconds(3600);
        Instant wayBack = now.minus(5, ChronoUnit.DAYS);

        // Comparisons
        boolean isBefore = parsed.isBefore(now);
        boolean isAfter = parsed.isAfter(now);
        int comparison = parsed.compareTo(now);

        // Calculate duration
        Duration timePassed = Duration.between(parsed, now);
        System.out.println("Duration: " + timePassed);

        // Convert to ZonedDateTime
        ZonedDateTime nyTime = now.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime tokyoTime = now.atZone(ZoneId.of("Asia/Tokyo"));

        System.out.println("Same instant in NY: " + nyTime);
        System.out.println("Same instant in Tokyo: " + tokyoTime);

        // Convert to/from java.util.Date
        Date legacyDate = Date.from(now);
        Instant fromDate = legacyDate.toInstant();

        // Truncate
        Instant truncated = now.truncatedTo(ChronoUnit.SECONDS);
        System.out.println("Truncated: " + truncated);

        // Practical: Database timestamp
        Instant dbTimestamp = Instant.now();
        // Store: dbTimestamp.toString() or dbTimestamp.toEpochMilli()

        // Practical: Measuring elapsed time
        Instant start = Instant.now();

        // Some operation
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant end = Instant.now();
        long elapsed = Duration.between(start, end).toMillis();
        System.out.println("Elapsed: " + elapsed + " ms");

        // Min and Max
        Instant min = Instant.MIN; // -1000000000-01-01T00:00Z
        Instant max = Instant.MAX; // +1000000000-12-31T23:59:59.999999999Z
    }
}
