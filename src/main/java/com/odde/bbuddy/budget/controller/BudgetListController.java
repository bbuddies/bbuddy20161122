package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.Budget;
import com.odde.bbuddy.budget.domain.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.odde.bbuddy.common.controller.Urls.BUDGETS;
import static com.odde.bbuddy.common.controller.Urls.BUDGETS_LIST;

/**
 * @author howie
 * @since 2016/11/23
 */
@Controller
public class BudgetListController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetListController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping(BUDGETS)
    public ModelAndView list() {

        List<Budget> budgets = budgetService.list();

        ModelAndView model = new ModelAndView(BUDGETS_LIST);
        model.addObject("budgets", budgets);

        return model;
    }

}
