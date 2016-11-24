package com.odde.bbuddy.budget.domain;

import java.time.LocalDate;
import java.time.YearMonth;

public class Period {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Period(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getFromMonthString() {
        return toMonthString(fromDate);
    }

    public String getToMonthString() {
        return toMonthString(toDate);
    }

    private String toMonthString(LocalDate date) {
        return YearMonth.from(date).toString();
    }

    public int getDayCount() {
        return toDate.getDayOfMonth() - fromDate.getDayOfMonth() + 1;
    }

    public int getOverlappingDayCount(Period another) {
        LocalDate startOfOverlapping = fromDate.isAfter(another.fromDate) ? fromDate : another.fromDate;
        LocalDate endOfOverlapping = toDate.isBefore(another.toDate) ? toDate : another.toDate;
        return new Period(startOfOverlapping, endOfOverlapping).getDayCount();
    }
}
