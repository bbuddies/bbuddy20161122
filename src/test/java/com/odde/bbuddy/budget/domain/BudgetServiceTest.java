package com.odde.bbuddy.budget.domain;

import com.odde.bbuddy.budget.repo.BudgetRepo;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

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

    @Test
    public void add_when_a_record_existed() throws Exception {
        BudgetRepo repository = mock(BudgetRepo.class);
        Budget oldBudget = new Budget();
        oldBudget.setId(1);
        oldBudget.setAmount(1000);
        oldBudget.setMonth("2017-10");
        when(repository.findByMonth("2017-10")).thenReturn(oldBudget);

        BudgetService budgetService = new BudgetService(repository);
        Budget budget = new Budget();
        budget.setAmount(2000);
        budget.setMonth("2017-10");

        budgetService.add(budget);

        ArgumentCaptor<Budget> captor = ArgumentCaptor.forClass(Budget.class);
        verify(repository).save(captor.capture());
        Budget savedBudget = captor.getValue();
        assertEquals(1, savedBudget.getId());
        assertEquals(2000, savedBudget.getAmount());
        assertEquals("2017-10", savedBudget.getMonth());
    }

    @Test
    public void testValidateBudget() throws Exception {
        BudgetRepo repository = mock(BudgetRepo.class);
        BudgetService budgetService = new BudgetService(repository);

        Budget budget = new Budget();
        budget.setAmount(500);
        budget.setMonth("2017-01");

        String targetMonth = "2017-03";
        when(repository.findByMonthLessThan(targetMonth)).thenReturn(budget);

        boolean isValid = budgetService.validateBudget(targetMonth);

        assertFalse(isValid);


    }
}