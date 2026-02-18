package com.pcgs.core.java.pocs.datetime;

import java.time.*;
import java.util.Date;

public class ConversionReference {

    // ========== TO INSTANT ==========
    public Instant toInstant() {
        // From Date
        Date date = new Date();
        Instant i1 = date.toInstant();

        // From ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now();
        Instant i2 = zdt.toInstant();

        // From LocalDateTime (needs zone)
        LocalDateTime ldt = LocalDateTime.now();
        Instant i3 = ldt.atZone(ZoneId.systemDefault()).toInstant();

        // From Timestamp
        java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
        Instant i4 = ts.toInstant();

        return i1;
    }

    // ========== TO LocalDateTime ==========
    public LocalDateTime toLocalDateTime() {
        // From Date
        Date date = new Date();
        LocalDateTime ldt1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        // From Instant
        Instant instant = Instant.now();
        LocalDateTime ldt2 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        // From ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now();
        LocalDateTime ldt3 = zdt.toLocalDateTime();

        // From Timestamp
        java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
        LocalDateTime ldt4 = ts.toLocalDateTime();

        return ldt1;
    }

    // ========== TO LocalDate ==========
    public LocalDate toLocalDate() {
        // From Date
        Date date = new Date();
        LocalDate ld1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // From Instant
        Instant instant = Instant.now();
        LocalDate ld2 = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        // From LocalDateTime
        LocalDateTime ldt = LocalDateTime.now();
        LocalDate ld3 = ldt.toLocalDate();

        // From sql.Date
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        LocalDate ld4 = sqlDate.toLocalDate();

        return ld1;
    }

    // ========== TO Date ==========
    public Date toDate() {
        // From Instant
        Instant instant = Instant.now();
        Date d1 = Date.from(instant);

        // From LocalDateTime
        LocalDateTime ldt = LocalDateTime.now();
        Date d2 = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        // From LocalDate
        LocalDate ld = LocalDate.now();
        Date d3 = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // From ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now();
        Date d4 = Date.from(zdt.toInstant());

        return d1;
    }

    // ========== TO sql.Date ==========
    public java.sql.Date toSqlDate() {
        // From LocalDate
        LocalDate ld = LocalDate.now();
        java.sql.Date sd1 = java.sql.Date.valueOf(ld);

        // From util.Date
        Date date = new Date();
        java.sql.Date sd2 = new java.sql.Date(date.getTime());

        return sd1;
    }

    // ========== TO Timestamp ==========
    public java.sql.Timestamp toTimestamp() {
        // From LocalDateTime
        LocalDateTime ldt = LocalDateTime.now();
        java.sql.Timestamp ts1 = java.sql.Timestamp.valueOf(ldt);

        // From Instant
        Instant instant = Instant.now();
        java.sql.Timestamp ts2 = java.sql.Timestamp.from(instant);

        // From util.Date
        Date date = new Date();
        java.sql.Timestamp ts3 = new java.sql.Timestamp(date.getTime());

        return ts1;
    }
}