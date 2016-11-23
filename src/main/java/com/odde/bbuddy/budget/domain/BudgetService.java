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
        if (!validateBudget(budget.getMonth())) {
            failure.run();
            return;
        }

        // 1. no records in table.
        if (repository.count() == 0) {
            repository.save(budget);
            return;
        }

        // 2. update specific record
        Budget savedBudget = repository.findByMonth(budget.getMonth());
        if (savedBudget != null) {
            savedBudget.setAmount(budget.getAmount());

            repository.save(savedBudget);
            return;
        }

        // 2. query previous month budget and next month budget
        String[] intentMonth = budget.getMonth()
                                     .split("-");
        String preMonth = String.format("%s-%02d", intentMonth[0], Integer.parseInt(intentMonth[1]) - 1);
        Budget previousMonth = repository.findByMonth(preMonth);

        String nextMonth = String.format("%s-%02d", intentMonth[0], Integer.parseInt(intentMonth[1]) + 1);
        Budget nextMonthBudget = repository.findByMonth(nextMonth);

        boolean isvalid = (previousMonth != null || nextMonthBudget != null);

        if (isvalid) {
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
        if (previousBudget == null)
            return true;
        else {
            String existedMonth = previousBudget.getMonth();
            String[] dateArr = existedMonth.split("-");
            int month = Integer.valueOf(dateArr[1]) + 1;

            String expectMonth = String.format("%s-%02d", dateArr[0], month);
            return expectMonth.equals(targetMonth);
        }
    }

    public double totalBudget(String from,
                              String to) {

        final LocalDate toDate = LocalDate.parse(to);
        final LocalDate fromDate = LocalDate.parse(from);

        List<Budget> budgets = repository.findBetween(from.substring(0, 7), to.substring(0, 7));

        return budgets.stream()
                      .mapToDouble(budget -> {

                          LocalDate budgetMonth = budget.thisMonth();
                          int days = budgetMonth.getMonth()
                                                .length(true);

                          int duration = 0;
                          if (fromDate.getMonth()
                                      .equals(toDate.getMonth())) {

                              duration = toDate.getDayOfMonth() - fromDate.getDayOfMonth() + 1;
                          }
                          else if (budgetMonth.getMonth()
                                              .equals(fromDate.getMonth())) {

                              duration = days - fromDate.getDayOfMonth() + 1;

                          }
                          else if (budgetMonth.getMonth()
                                              .equals(toDate.getMonth())) {

                              duration = toDate.getDayOfMonth();
                          }

                          else {
                              duration = days;
                          }

                          return new BigDecimal(budget.getAmount()).divide(new BigDecimal(days),
                                                                           BigDecimal.ROUND_HALF_UP)
                                                                   .multiply(new BigDecimal(duration))
                                                                   .doubleValue();

                      })
                      .sum();

    }
}
