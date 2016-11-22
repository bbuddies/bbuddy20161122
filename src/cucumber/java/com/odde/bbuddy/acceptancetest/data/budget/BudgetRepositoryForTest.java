package com.odde.bbuddy.acceptancetest.data.budget;

import com.odde.bbuddy.budget.domain.Budget;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zbcjackson on 22/11/2016.
 */
@Transactional
public interface BudgetRepositoryForTest extends Repository<Budget, Long> {
    List<Budget> findAll();

    void save(Budget budget);
}
