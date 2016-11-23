package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.BudgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zbcjackson on 22/11/2016.
 */
@Service
public class BudgetService {
    private final BudgetRepo repository;

    @Autowired
    public BudgetService(BudgetRepo repository) {
        this.repository = repository;
    }

    public void add(Budget budget) {
        Budget savedBudget = repository.findByMonth(budget.getMonth());
        if (savedBudget != null) {
            savedBudget.setAmount(budget.getAmount());

            repository.save(savedBudget);
        }
        else {
            repository.save(budget);
        }
    }

    public List<Budget> list() {

        return repository.findAll()
                         .stream()
                         .sorted((b1, b2) -> b1.getMonth()
                                               .compareTo(b2.getMonth()))
                         .collect(Collectors.toList());

    }
}
