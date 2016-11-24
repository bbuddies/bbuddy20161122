package com.odde.bbuddy.budget.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author zbcjackson
 * @since 22/11/2016
 *
 */
@Entity
@Table(name = "budgets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int amount;
    private String month;

    @Transient
    private LocalDate thisMonth() {
        return LocalDate.parse(month + "-01");
    }

    private int getDayCount() {
        return thisMonth().getMonth().length(true);
    }

    private Period getPeriod() {
        return new Period(thisMonth(), thisMonth().plusMonths(1).minusDays(1));
    }

    private double getDailyAmount() {
        return amount / getDayCount();
    }

    public double getOverlappingAmount(Period period) {
        return getDailyAmount() * period.getOverlappingDayCount(getPeriod());
    }
}
