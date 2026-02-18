package com.pcgs.core.java.pocs.datetime;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConversionExample {
    public static void main(String[] args) {

        // ============================================
        // java.util.Date to java.time
        // ============================================

        Date legacyDate = new Date();

        // 1. Date -> Instant
        Instant instant = legacyDate.toInstant();
        System.out.println("Instant: " + instant);

        // 2. Date -> ZonedDateTime
        ZonedDateTime zonedDateTime = legacyDate.toInstant()
                .atZone(ZoneId.systemDefault());
        System.out.println("ZonedDateTime: " + zonedDateTime);

        // 3. Date -> LocalDateTime
        LocalDateTime localDateTime = legacyDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println("LocalDateTime: " + localDateTime);

        // Alternative using LocalDateTime.ofInstant
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(
                legacyDate.toInstant(),
                ZoneId.systemDefault()
        );

        // 4. Date -> LocalDate
        LocalDate localDate = legacyDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println("LocalDate: " + localDate);

        // ============================================
        // java.time to java.util.Date
        // ============================================

        // 1. Instant -> Date
        Instant now = Instant.now();
        Date dateFromInstant = Date.from(now);
        System.out.println("Date from Instant: " + dateFromInstant);

        // 2. ZonedDateTime -> Date
        ZonedDateTime zdt = ZonedDateTime.now();
        Date dateFromZoned = Date.from(zdt.toInstant());
        System.out.println("Date from ZonedDateTime: " + dateFromZoned);

        // 3. LocalDateTime -> Date
        LocalDateTime ldt = LocalDateTime.now();
        Date dateFromLocal = Date.from(
                ldt.atZone(ZoneId.systemDefault()).toInstant()
        );
        System.out.println("Date from LocalDateTime: " + dateFromLocal);

        // 4. LocalDate -> Date (at start of day)
        LocalDate ld = LocalDate.now();
        Date dateFromLocalDate = Date.from(
                ld.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        System.out.println("Date from LocalDate: " + dateFromLocalDate);

        // ============================================
        // java.sql.Date conversions
        // ============================================

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        // 1. sql.Date -> LocalDate
        LocalDate localDateFromSql = sqlDate.toLocalDate();
        System.out.println("LocalDate from sql.Date: " + localDateFromSql);

        // 2. LocalDate -> sql.Date
        LocalDate ld2 = LocalDate.of(2024, 12, 25);
        java.sql.Date sqlDateFromLocal = java.sql.Date.valueOf(ld2);
        System.out.println("sql.Date from LocalDate: " + sqlDateFromLocal);

        // ============================================
        // java.sql.Timestamp conversions
        // ============================================

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

        // 1. Timestamp -> LocalDateTime
        LocalDateTime ldtFromTimestamp = timestamp.toLocalDateTime();
        System.out.println("LocalDateTime from Timestamp: " + ldtFromTimestamp);

        // 2. Timestamp -> Instant
        Instant instantFromTimestamp = timestamp.toInstant();
        System.out.println("Instant from Timestamp: " + instantFromTimestamp);

        // 3. LocalDateTime -> Timestamp
        LocalDateTime ldt2 = LocalDateTime.of(2024, 12, 25, 15, 30);
        java.sql.Timestamp timestampFromLocal = java.sql.Timestamp.valueOf(ldt2);
        System.out.println("Timestamp from LocalDateTime: " + timestampFromLocal);

        // 4. Instant -> Timestamp
        Instant instant2 = Instant.now();
        java.sql.Timestamp timestampFromInstant = java.sql.Timestamp.from(instant2);
        System.out.println("Timestamp from Instant: " + timestampFromInstant);

        // ============================================
        // Practical example: Database operations
        // ============================================

        demonstrateDbOperations();
    }

    public static void demonstrateDbOperations() {
        System.out.println("\n=== Database Operations ===");

        // Saving to database
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        java.sql.Date dbDate = java.sql.Date.valueOf(birthDate);
        // INSERT INTO users (birth_date) VALUES (?)
        // preparedStatement.setDate(1, dbDate);

        LocalDateTime createdAt = LocalDateTime.now();
        java.sql.Timestamp dbTimestamp = java.sql.Timestamp.valueOf(createdAt);
        // INSERT INTO users (created_at) VALUES (?)
        // preparedStatement.setTimestamp(1, dbTimestamp);

        // Reading from database
        // java.sql.Date dateFromDb = resultSet.getDate("birth_date");
        // LocalDate birthDateFromDb = dateFromDb.toLocalDate();

        // java.sql.Timestamp timestampFromDb = resultSet.getTimestamp("created_at");
        // LocalDateTime createdAtFromDb = timestampFromDb.toLocalDateTime();

        System.out.println("DB Date: " + dbDate);
        System.out.println("DB Timestamp: " + dbTimestamp);
    }
}