package com.odde.bbuddy.budget.repo;

import com.odde.bbuddy.budget.domain.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zbcjackson
 * @since 22/11/2016
 */
@Transactional
public interface BudgetRepo extends Repository<Budget, Long> {
    void save(Budget budget);

    Budget findByMonth(String month);

    List<Budget> findAll();

    Budget findByMonthLessThan(String targetMonth);

    @Query(nativeQuery = true,
           value = "select * from budgets b where b.month between :from and :to  ")
    List<Budget> findBetween(String from,
                             String to);
}
