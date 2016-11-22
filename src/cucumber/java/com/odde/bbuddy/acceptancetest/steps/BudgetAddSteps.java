package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.budget.BudgetRepositoryForTest;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.budget.domain.Budget;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zbcjackson on 22/11/2016.
 */
public class BudgetAddSteps {
    @Autowired
    UiDriver driver;

    @Autowired
    BudgetRepositoryForTest budgetRepository;

    @When("^Add budget (\\d+) for \"([^\"]*)\"$")
    public void add_budget_for(int amount, String month) throws Throwable {
        driver.navigateTo("/budgets/add");
        driver.findElementByName("amount").sendKeys(String.valueOf(amount));
        driver.findElementByName("month").sendKeys(month);
        driver.findElementByName("amount").submit();
    }

    @Then("^exists budget (\\d+) for \"([^\"]*)\"$")
    public void exists_budget_for(int arg1, String arg2) throws Throwable {
        List<Budget> budgets = budgetRepository.findAll();
        assertEquals(1, budgets.size());
        assertEquals(arg1, budgets.get(0).getAmount());
        assertEquals(arg2, budgets.get(0).getMonth());
    }

    @Given("^budget (\\d+) for \"([^\"]*)\" already exist$")
    public void budget_for_already_exist(int amount, String month) throws Throwable {
        Budget budgetOld = new Budget();
        budgetOld.setAmount(1000);
        budgetOld.setMonth("2017-10");

        budgetRepository.save(budgetOld);

    }

}
