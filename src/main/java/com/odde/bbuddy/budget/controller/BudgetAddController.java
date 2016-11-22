package com.odde.bbuddy.budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zbcjackson on 22/11/2016.
 */
@Controller
public class BudgetAddController {
    @GetMapping("/budgets/add")
    public String add(){
        return "/budgets/add";
    }
}
