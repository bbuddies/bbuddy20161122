package com.odde.bbuddy.budget.repo;

import com.odde.bbuddy.budget.domain.Budget;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author zbcjackson
 * @since 22/11/2016
 *
 */
@Transactional
public interface BudgetRepo extends Repository<Budget, Long> {
    void save(Budget budget);

    Budget findByMonth(String month);

    List<Budget> findAll();

    Budget findByMonthLessThan(String targetMonth);

    Budget findOneByMonthLessThanOrderByMonthDesc(String targetMonth);

    Budget findOneByMonthGreaterThanOrderByMonth(String intentBuddgetMonth);

    int count();
}
