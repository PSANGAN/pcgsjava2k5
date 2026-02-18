package com.pcgs.core.java.pocs.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ZonedDateTimeExample {
    public static void main(String[] args) {
        // Creating ZonedDateTime
        ZonedDateTime nowInSystemZone = ZonedDateTime.now();
        System.out.println("Now in system zone: " + nowInSystemZone);

        ZonedDateTime nowInNY = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime nowInTokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime nowInLondon = ZonedDateTime.now(ZoneId.of("Europe/London"));

        System.out.println("New York: " + nowInNY);
        System.out.println("Tokyo: " + nowInTokyo);
        System.out.println("London: " + nowInLondon);

        // From LocalDateTime
        LocalDateTime localDT = LocalDateTime.of(2024, 12, 25, 15, 30);
        ZonedDateTime zonedFromLocal = localDT.atZone(ZoneId.of("America/New_York"));
        System.out.println("From LocalDateTime: " + zonedFromLocal);

        // From Instant
        Instant instant = Instant.now();
        ZonedDateTime zonedFromInstant = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println("From Instant: " + zonedFromInstant);

        // Parse
        ZonedDateTime parsed = ZonedDateTime.parse("2024-12-25T15:30:45+05:30[Asia/Kolkata]");
        System.out.println("Parsed: " + parsed);

        // Get all available zone IDs
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        System.out.println("Total zones: " + allZones.size());
        // Print some examples
        allZones.stream().filter(z -> z.startsWith("America/")).limit(5).forEach(System.out::println);

        // ZoneId operations
        ZoneId nyZone = ZoneId.of("America/New_York");
        ZoneId utcZone = ZoneId.of("UTC");
        ZoneId systemZone = ZoneId.systemDefault();

        // ZoneOffset (fixed offset from UTC)
        ZoneOffset offset = ZoneOffset.of("+05:30"); // India
        ZoneOffset utcOffset = ZoneOffset.UTC;
        ZonedDateTime withOffset = ZonedDateTime.now(offset);

        // Convert between zones
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime tokyoTime = nyTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

        System.out.println("NY Time: " + nyTime);
        System.out.println("Same instant in Tokyo: " + tokyoTime);

        // Keep local time, change zone (rarely used)
        ZonedDateTime sameLocalDifferentZone = nyTime.withZoneSameLocal(ZoneId.of("Asia/Tokyo"));
        System.out.println("Same local time, different zone: " + sameLocalDifferentZone);

        // Getting components
        LocalDateTime localDateTime = nowInSystemZone.toLocalDateTime();
        LocalDate localDate = nowInSystemZone.toLocalDate();
        LocalTime localTime = nowInSystemZone.toLocalTime();
        ZoneId zone = nowInSystemZone.getZone();
        ZoneOffset currentOffset = nowInSystemZone.getOffset();

        // Manipulating
        ZonedDateTime tomorrow = nowInSystemZone.plusDays(1);
        ZonedDateTime nextWeek = nowInSystemZone.plusWeeks(1);
        ZonedDateTime nextHour = nowInSystemZone.plusHours(1);

        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formatted = nowInSystemZone.format(formatter);
        System.out.println("Formatted: " + formatted);

        // Full formatter with zone
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy HH:mm:ss z VV");
        String fullFormatted = nowInSystemZone.format(fullFormatter);
        System.out.println("Full formatted: " + fullFormatted);

        // Convert to Instant
        Instant instantFromZoned = nowInSystemZone.toInstant();

        // Practical: Meeting scheduler across timezones
        scheduleMeeting();

        // Practical: Handling DST
        demonstrateDST();
    }

    public static void scheduleMeeting() {
        // Meeting at 3 PM New York time
        ZonedDateTime nyMeeting = ZonedDateTime.of(
                2024, 6, 15, 15, 0, 0, 0,
                ZoneId.of("America/New_York")
        );

        // What time for participants in different zones?
        ZonedDateTime londonMeeting = nyMeeting.withZoneSameInstant(ZoneId.of("Europe/London"));
        ZonedDateTime tokyoMeeting = nyMeeting.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime indiaMeeting = nyMeeting.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

        System.out.println("\nMeeting Times:");
        System.out.println("New York: " + nyMeeting.format(DateTimeFormatter.ofPattern("HH:mm z")));
        System.out.println("London: " + londonMeeting.format(DateTimeFormatter.ofPattern("HH:mm z")));
        System.out.println("Tokyo: " + tokyoMeeting.format(DateTimeFormatter.ofPattern("HH:mm z")));
        System.out.println("India: " + indiaMeeting.format(DateTimeFormatter.ofPattern("HH:mm z")));
    }

    public static void demonstrateDST() {
        // Before DST starts (Standard Time)
        ZonedDateTime beforeDST = ZonedDateTime.of(
                2024, 3, 10, 1, 30, 0, 0,
                ZoneId.of("America/New_York")
        );

        // Add 1 hour - crosses DST boundary
        ZonedDateTime afterDST = beforeDST.plusHours(1);

        System.out.println("\nDST Demonstration:");
        System.out.println("Before DST: " + beforeDST + " (Offset: " + beforeDST.getOffset() + ")");
        System.out.println("After DST: " + afterDST + " (Offset: " + afterDST.getOffset() + ")");

        // Note: The clock "springs forward", so 2:30 AM doesn't exist on DST start day
    }
}
