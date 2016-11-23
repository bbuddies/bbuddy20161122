package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.BudgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zbcjackson
 * @since 22/11/2016
 */
@Service
public class BudgetService {
    private final BudgetRepo repository;

    @Autowired
    public BudgetService(BudgetRepo repository) {
        this.repository = repository;
    }

    public void add(Budget budget,
                    Runnable failure) {
        if (!validateBudget(budget)) {
            failure.run();
            return;
        }

        Budget savedBudget = repository.findByMonth(budget.getMonth());
        if (savedBudget != null) {
            savedBudget.setAmount(budget.getAmount());
            repository.save(savedBudget);
        }else {
            repository.save(budget);
        }
    }

    private boolean hasPreviousOrCurrentOrNextBudget(Budget budget) {
        return getBudgetByMonthOffset(budget, 0) != null || getPreviousBudget(budget) != null || getNextBudget(budget) != null;
    }

    private Budget getNextBudget(Budget budget) {
        return getBudgetByMonthOffset(budget, 1);
    }

    private Budget getPreviousBudget(Budget budget) {
        return getBudgetByMonthOffset(budget, -1);
    }


    private Budget getBudgetByMonthOffset(Budget budget, int offset){
        String[] intentMonth = budget.getMonth().split("-");
        int monthNumber = Integer.parseInt(intentMonth[1]) + offset;
        return repository.findByMonth(String.format("%s-%02d", intentMonth[0] , monthNumber));
    }


    public List<Budget> list() {

        return repository.findAll()
                         .stream()
                         .sorted((b1, b2) -> b1.getMonth()
                                               .compareTo(b2.getMonth()))
                         .collect(Collectors.toList());
    }

    public boolean validateBudget(Budget budget) {
        return repository.count() == 0 || hasPreviousOrCurrentOrNextBudget(budget);
    }

    public double totalBudget(String from,
                              String to) {

        List<Budget> budgets = repository.findBetween(from.substring(0, 7), to.substring(0, 7));
        return budgets.stream()
                      .mapToDouble(budget -> {

                          LocalDate bugetMonth = LocalDate.parse(budget.getMonth() + "-01");
                          LocalDate toDate = LocalDate.parse(to);
                          LocalDate fromDate = LocalDate.parse(from);
                          int days = fromDate.getMonth()
                                             .length(true);

                          int duration = 0;
                          if (bugetMonth.isBefore(toDate)) {

                              duration = toDate.getDayOfMonth() - fromDate.getDayOfMonth() + 1;

                          }
                          else {

                              //TODO  more than two month

                          }

                          System.out.println(days + " " + duration);
                          return new BigDecimal(budget.getAmount()).divide(new BigDecimal(days),
                                                                           BigDecimal.ROUND_HALF_UP)
                                                                   .multiply(new BigDecimal(duration))
                                                                   .doubleValue();

                      })
                      .sum();

    }
}
