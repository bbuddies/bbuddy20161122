package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.BudgetRepo;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by zbcjackson on 22/11/2016.
 */
public class BudgetServiceTest {
    @Test
    public void add_by_repository() throws Exception {
        BudgetRepo repository = mock(BudgetRepo.class);
        BudgetService budgetService = new BudgetService(repository);
        Budget budget = new Budget();

        budgetService.add(budget);

        verify(repository).save(budget);
    }
}