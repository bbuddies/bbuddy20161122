package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.Budget;
import com.odde.bbuddy.budget.domain.BudgetService;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by zbcjackson on 22/11/2016.
 */
public class BudgetAddControllerTest {
    BudgetService service = mock(BudgetService.class);
    BudgetAddController controller = new BudgetAddController(service);
    Budget budget = new Budget();

    @Test
    public void save_budget_view() throws Exception {
        assertEquals("/budgets/add", controller.save(budget));
    }

    @Test
    public void save_budget_by_service() throws Exception {
        controller.save(budget);
        verify(service).add(budget);
    }
}