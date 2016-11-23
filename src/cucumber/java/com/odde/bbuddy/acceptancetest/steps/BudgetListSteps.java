package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.budget.BudgetRepositoryForTest;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.budget.domain.Budget;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author howie
 * @since 2016/11/23
 */
public class BudgetListSteps {

    @Autowired
    UiDriver driver;

    @Autowired
    CommonPage commonPage;

    @Autowired
    BudgetRepositoryForTest budgetRepositoryForTest;

    @Given("^has budgets$")
    public void has_budgets(List<Budget> budgets) throws Throwable {
        budgets.forEach(budgetRepositoryForTest::save);
    }

    @When("^search$")
    public void search() throws Throwable {
        driver.navigateTo("/budgets");

    }

    @Then("^list budgets$")
    public void list_budgets(List<Budget> budgets) throws Throwable {

        String allText = commonPage.getAllText();

        budgets.forEach(budget -> {
            Assert.assertTrue(allText.contains(budget.getMonth()));
            Assert.assertTrue(allText.contains(String.valueOf(budget.getAmount())));

        });

    }

}
