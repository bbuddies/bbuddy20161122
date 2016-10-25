package com.odde.bbuddy.acceptancetest.steps;

import com.odde.bbuddy.acceptancetest.data.ApplicationConfigurations;
import com.odde.bbuddy.acceptancetest.data.transaction.TransactionRepoForTest;
import com.odde.bbuddy.acceptancetest.pages.CommonPage;
import com.odde.bbuddy.acceptancetest.pages.ShowAllTransactionsPage;
import com.odde.bbuddy.transaction.domain.Transaction;
import com.odde.bbuddy.transaction.view.PresentableTransaction;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.odde.bbuddy.common.Formats.DAY;
import static com.odde.bbuddy.common.page.PageableFactory.PER_PAGE_LIMIT_PROPERTY_NAME;
import static com.odde.bbuddy.transaction.builder.TransactionBuilder.defaultTransaction;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionListSteps {

    @Autowired
    TransactionRepoForTest transactionRepo;

    @Autowired
    ShowAllTransactionsPage showAllTransactionsPage;

    @Autowired
    CommonPage commonPage;

    @Autowired
    ApplicationConfigurations applicationConfigurations;

    @Given("^exists the following transactions$")
    public void exists_the_following_transactions(@Format(DAY) List<Transaction> transactions) throws Throwable {
        transactions.forEach(transaction -> transactionRepo.save(transaction));
    }

    @When("^show all transactions$")
    public void show_all_transactions() throws Throwable {
        showAllTransactionsPage.show();
    }

    @Then("^you will see all transactions as below$")
    public void you_will_see_all_transactions_as_below(@Format(DAY) List<PresentableTransaction> transactions) throws Throwable {
        transactions.forEach(transaction -> assertThat(commonPage.getAllText()).contains(transaction.allViewText()));
    }

    @When("^show total of all transactions$")
    public void show_total_of_all_transactions() throws Throwable {
        show_all_transactions();
    }

    @Then("^you will see the total as below$")
    public void you_will_see_the_total_as_below(Map<String, String> totals) throws Throwable {
        assertThat(commonPage.getAllText()).contains(totals.values());
    }

    @Given("^exists (\\d+) transactions$")
    public void exists_transactions(int count) throws Throwable {
        amountOfTransactions(count).forEach(this::createTransactionWithAmount);
    }

    @Given("^every page will display (\\d+) transactions$")
    public void every_page_will_display_transactions(int perPageLimit) throws Throwable {
        applicationConfigurations.overwrite(PER_PAGE_LIMIT_PROPERTY_NAME, perPageLimit);
    }

    @When("^show page (\\d+)$")
    public void show_page(int pageNumber) throws Throwable {
        showAllTransactionsPage.navigateToPage(pageNumber);
    }

    @Then("^you will see (\\d+) transactions in page (\\d+)$")
    public void you_will_see_transactions_in_page(int transactionCount, int pageNumber) throws Throwable {
        assertThat(commonPage.getAllText()).contains(amountTextOfTransactionsInPage(pageNumber, 10, transactionCount));
    }

    private List<String> amountTextOfTransactionsInPage(int pageNumber, int perPageCount, int transactionCount) {
        return amountOfTransactionsInPage(pageNumber, transactionCount, perPageCount).mapToObj(String::valueOf).collect(toList());
    }

    private IntStream amountOfTransactionsInPage(int pageNumber, int transactionCount, int perPageCount) {
        return range(amountOfFirstTransaction(pageNumber, perPageCount), amountOfFirstTransaction(pageNumber, perPageCount)+transactionCount);
    }

    private int amountOfFirstTransaction(int pageNumber, int perPageCount) {
        return (pageNumber-1) * perPageCount + 1;
    }

    private IntStream amountOfTransactions(int count) {
        return range(1, count+1);
    }

    private void createTransactionWithAmount(int amount) {
        transactionRepo.save(defaultTransaction().amount(amount).build());
    }

}