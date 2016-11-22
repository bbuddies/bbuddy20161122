package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zbcjackson on 22/11/2016.
 */
public class BudgetAddSteps {
    @Autowired
    UiDriver driver;

    @When("^Add budget (\\d+) for \"([^\"]*)\"$")
    public void add_budget_for(int amount, String month) throws Throwable {
        driver.navigateTo("/budgets/add");
        driver.findElementByName("amount").sendKeys(String.valueOf(amount));
        driver.findElementByName("month").sendKeys(month);
        driver.findElementByName("amount").submit();
    }

    @Then("^exists budget (\\d+) for \"([^\"]*)\"$")
    public void exists_budget_for(int arg1, String arg2) throws Throwable {

    }
}
