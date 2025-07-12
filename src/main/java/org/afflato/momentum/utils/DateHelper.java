package org.afflato.momentum.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateHelper {

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        System.out.println("Current date in YYYYMMDD format: " + formattedDate);
        return formattedDate;
    }

    public static String lastPublishDate() {
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfCurrentMonth = now.withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = firstDayOfCurrentMonth.minusDays(1);
        LocalDate lastFriday = lastDayOfPreviousMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = lastFriday.format(formatter);

        System.out.println("Last Friday of previous month in YYYYMMDD format: " + formattedDate);
        return formattedDate;
    }

    public static String getLastWorkingDay() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the last day of the previous month
        YearMonth previousMonth = YearMonth.from(currentDate.minusMonths(1));
        LocalDate lastDayOfPreviousMonth = previousMonth.atEndOfMonth();

        // Calculate the last working day
        LocalDate lastWorkingDay = getLastWorkingDay(lastDayOfPreviousMonth);

        // Format the date in yyyyMMdd format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = lastWorkingDay.format(formatter);

        System.out.println("Last working day of the previous month: " + formattedDate);
        return formattedDate;
    }

    public static String getLastLastWorkingDay() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the last day of the month before the previous month
        YearMonth lastToLastMonth = YearMonth.from(currentDate.minusMonths(2));
        LocalDate lastDayOfLastToLastMonth = lastToLastMonth.atEndOfMonth();

        // Calculate the last working day
        LocalDate lastWorkingDay = getLastWorkingDay(lastDayOfLastToLastMonth);

        // Format the date in yyyyMMdd format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = lastWorkingDay.format(formatter);

        System.out.println("Last working day of the month before the previous month: " + formattedDate);
        return formattedDate;
    }

    private static LocalDate getLastWorkingDay(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
                return date.minusDays(1);
            case SUNDAY:
                return date.minusDays(2);
            default:
                return date;
        }
    }
}
