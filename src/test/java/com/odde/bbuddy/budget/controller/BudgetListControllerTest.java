package com.odde.bbuddy.budget.controller;

import com.google.common.collect.Lists;
import com.odde.bbuddy.budget.domain.Budget;
import com.odde.bbuddy.budget.domain.BudgetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.odde.bbuddy.common.controller.Urls.BUDGETS_LIST;
import static org.mockito.Mockito.when;

/**
 * @author howie
 * @since 2016/11/23
 */
@RunWith(MockitoJUnitRunner.class)
public class BudgetListControllerTest {

    @Mock
    private BudgetService budgetService;

    @InjectMocks
    private BudgetListController budgetListController;

    @Test
    public void list() throws Exception {

        List<Budget> budgets = Lists.newArrayList();
        budgets.add(new Budget(1, 1500, "2016-06"));
        ModelAndView mockModelAndView = new ModelAndView(BUDGETS_LIST);
        mockModelAndView.addObject("budgets", budgets);
        when(budgetService.list()).thenReturn(budgets);

        ModelAndView modelAndView = budgetListController.list();

        Assert.assertEquals(modelAndView.getViewName(), mockModelAndView.getViewName());
        Assert.assertEquals(modelAndView.getModel(), mockModelAndView.getModel());
    }

}