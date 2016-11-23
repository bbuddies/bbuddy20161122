package com.odde.bbuddy.budget.repo;

import com.odde.bbuddy.budget.domain.Budget;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zbcjackson on 22/11/2016.
 */
@Transactional
public interface BudgetRepo extends Repository<Budget, Long> {
    void save(Budget budget);

    Budget findByMonth(String month);

    Budget findByMonthLessThan(String targetMonth);
}
