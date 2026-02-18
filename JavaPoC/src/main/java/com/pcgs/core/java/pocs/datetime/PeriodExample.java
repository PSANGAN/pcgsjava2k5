package com.pcgs.core.java.pocs.datetime;

import java.time.Period;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PeriodExample {
    public static void main(String[] args) {
        // Creating Period
        Period oneYear = Period.ofYears(1);
        Period sixMonths = Period.ofMonths(6);
        Period tenDays = Period.ofDays(10);

        // Complex period
        Period complex = Period.of(2, 6, 15); // 2 years, 6 months, 15 days
        System.out.println("Complex: " + complex); // P2Y6M15D

        // Between dates
        LocalDate start = LocalDate.of(2020, 1, 15);
        LocalDate end = LocalDate.of(2024, 8, 20);
        Period age = Period.between(start, end);
        System.out.println("Period: " + age); // P4Y7M5D

        // Parse from ISO-8601
        Period parsed = Period.parse("P3Y2M10D");
        System.out.println("Parsed: " + parsed);

        // Getting components
        int years = complex.getYears();
        int months = complex.getMonths();
        int days = complex.getDays();

        System.out.println(years + " years, " + months + " months, " + days + " days");

        // Total months (years * 12 + months)
        long totalMonths = complex.toTotalMonths();
        System.out.println("Total months: " + totalMonths);

        // Arithmetic
        Period doubled = complex.multipliedBy(2);
        Period added = complex.plus(Period.ofMonths(3));
        Period subtracted = complex.minus(Period.ofDays(5));
        Period negated = complex.negated();

        // Normalize (converts excess days/months)
        Period unnormalized = Period.of(0, 25, 0); // 25 months
        Period normalized = unnormalized.normalized(); // 2 years, 1 month
        System.out.println("Normalized: " + normalized);

        // Comparisons
        boolean isZero = complex.isZero();
        boolean isNegative = complex.isNegative();

        // Practical: Calculate age
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate currentDate = LocalDate.now();
        Period ageCalc = Period.between(birthDate, currentDate);

        System.out.println("Age: " + ageCalc.getYears() + " years, " +
                ageCalc.getMonths() + " months, " +
                ageCalc.getDays() + " days");

        // Add period to date
        LocalDate futureDate = currentDate.plus(Period.ofYears(5));
        System.out.println("5 years from now: " + futureDate);

        // Practical: Subscription expiry
        LocalDate subscriptionStart = LocalDate.now();
        Period subscriptionDuration = Period.ofMonths(12);
        LocalDate expiryDate = subscriptionStart.plus(subscriptionDuration);
        System.out.println("Subscription expires: " + expiryDate);
    }
}
