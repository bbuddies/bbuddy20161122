package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.Budget;
import com.odde.bbuddy.budget.domain.BudgetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by zbcjackson on 22/11/2016.
 */
public class BudgetAddControllerTest {
    BudgetService service = mock(BudgetService.class);
    BudgetAddController controller = new BudgetAddController(service);
    Budget budget;
    Model model = mock(Model.class);

    @Before
    public void init() {
        budget = new Budget();
    }

    @Test
    public void save_budget_view() throws Exception {
        assertEquals("/budgets/add", controller.save(budget, model));
    }

    @Test
    public void save_budget_by_service() throws Exception {
        controller.save(budget, model);
        verify(service).add(eq(budget), any());
    }

    @Test
    public void save_budget_invalid_budget() throws Exception {
        doAnswer(invocation -> {
            Runnable failure = invocation.getArgumentAt(1, Runnable.class);
            failure.run();
            return null;
        }).when(service).add(eq(budget), any());
        controller.save(budget, model);
        verify(model).addAttribute("errorMsg", "error");
    }
}