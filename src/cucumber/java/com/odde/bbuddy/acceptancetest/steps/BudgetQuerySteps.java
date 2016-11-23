package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.budget.BudgetRepositoryForTest;
import com.odde.bbuddy.acceptancetest.driver.UiDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.odde.bbuddy.common.controller.Urls.BUDGETS_QUERY;

/**
 * @author howie
 * @since 2016/11/23
 */
public class BudgetQuerySteps {

    @Autowired
    UiDriver driver;

    @Autowired
    BudgetRepositoryForTest budgetRepositoryForTest;

    @When("^query from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void query_from_to(String from,
                              String to) throws Throwable {
        driver.navigateTo(BUDGETS_QUERY);
        driver.findElementByName("from")
              .sendKeys(from);
        driver.findElementByName("to")
              .sendKeys(to);
        driver.findElementByName("from")
              .submit();

    }

    @Then("^show budget (\\d+)\\.(\\d+)$")
    public void show_budget(int arg1,
                            int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

}
