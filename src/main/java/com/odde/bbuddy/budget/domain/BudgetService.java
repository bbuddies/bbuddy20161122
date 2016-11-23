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

    public boolean validateBudget(String targetMonth) {
        Budget previousBudget = repository.findByMonthLessThan(targetMonth);
        if(previousBudget == null)
            return true;
        else {
            String existedMonth = previousBudget.getMonth();
            String[] dateArr = existedMonth.split("-");
            int month = Integer.valueOf(dateArr[1]) + 1;

            String expectMonth = String.format("%s-%02d", dateArr[0], month);
            return expectMonth.equals(targetMonth);
        }
    }
}
