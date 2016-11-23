package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.odde.bbuddy.common.controller.Urls.*;

/**
 * @author howie
 * @since 2016/11/23
 */
@Controller
public class BudgetQueryController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetQueryController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping(BUDGETS_QUERY)
    public String index() {

        return BUDGETS_QUERY;
    }

    @PostMapping(BUDGETS)
    public ModelAndView caculate(String from,
                                 String to) {

        double totalBudget = budgetService.totalBudget(from, to);

        ModelAndView model = new ModelAndView(BUDGETS_QUERY);
        model.addObject("total-budget", totalBudget);

        //TODO error msg

        return model;
    }

}
