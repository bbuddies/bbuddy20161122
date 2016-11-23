package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.Budget;
import com.odde.bbuddy.budget.domain.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author howie
 * @since 2016/11/23
 */
@Controller
public class BudgetListController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/budgets")
    public ModelAndView list() {

        List<Budget> budgets = budgetService.list();

        ModelAndView model = new ModelAndView("/budgets/list");
        model.addObject("budgets", budgets);

        return model;
    }

}
