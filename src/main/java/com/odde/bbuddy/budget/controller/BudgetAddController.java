package com.odde.bbuddy.budget.controller;

import com.odde.bbuddy.budget.domain.Budget;
import com.odde.bbuddy.budget.domain.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by zbcjackson on 22/11/2016.
 */
@Controller
public class BudgetAddController {
    private final BudgetService service;

    @Autowired
    public BudgetAddController(BudgetService service) {
        this.service = service;
    }

    @GetMapping("/budgets/add")
    public String add() {
        return "/budgets/add";
    }

    @PostMapping("/budgets/add")
    public String save(@ModelAttribute Budget budget,
                       Model model) {

        String inputMonth = budget.getMonth();
        if (service.validateBudget(inputMonth)) {
            service.add(budget);
        }
        else {
            model.addAttribute("errorMsg", "error");
        }

        return "/budgets/add";
    }
}
