package com.odde.bbuddy.budget.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by zbcjackson on 22/11/2016.
 */
@Entity
@Table(name = "budgets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int amount;
    private String month;
}
