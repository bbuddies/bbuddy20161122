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
    public LocalDate thisMonth() {

        return LocalDate.parse(month + "-01");
    }
}
