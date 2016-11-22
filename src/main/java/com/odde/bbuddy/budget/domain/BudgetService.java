package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.BudgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(savedBudget != null){
            savedBudget.setAmount(budget.getAmount());

            repository.save(savedBudget);
        }
        else {
            repository.save(budget);
        }
    }
}
