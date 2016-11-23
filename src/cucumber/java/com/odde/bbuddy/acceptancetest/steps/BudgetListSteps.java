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
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        //List<Budget> data=budgets.asList(Budget.class);
        budgets.forEach(budget -> {
            System.out.println(budget);
            budgetRepositoryForTest.save(budget);
        });

        Assert.assertEquals(3, budgets.size());

    }

    @When("^search$")
    public void search() throws Throwable {

        driver.navigateTo("/budgets");

        //        budgetRepositoryForTest.findAll();

        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
    }

    @Then("^list budgets$")
    public void list_budgets(List<Budget> budgets) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        //throw new PendingException();
        driver.navigateTo("/budgets");
        //driver. findElementByName("budgets");

        System.out.println(commonPage.getAllText());
        Assert.assertTrue(commonPage.getAllText()
                                    .contains("2016-07"));
        Assert.assertTrue(commonPage.getAllText()
                                    .contains("2016-08"));
        Assert.assertTrue(commonPage.getAllText()
                                    .contains("2016-09"));

    }

}
