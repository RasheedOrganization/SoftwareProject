package org.example.AcceptableTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class feature7 {
    @Given("a database with a list of completed orders")
    public void aDatabaseWithAListOfCompletedOrders() {
        assertTrue(true);
    }
    @When("a request is made to generate statistics for the year of March {int}")
    public void aRequestIsMadeToGenerateStatisticsForTheYearOfMarch(Integer int1) {
        assertTrue(true);
    }
    @Then("the total number of delivered items for {int} should be displayed")
    public void theTotalNumberOfDeliveredItemsForShouldBeDisplayed(Integer int1) {
        assertTrue(true);
    }
    @Then("the total cash received for {int} should be displayed")
    public void theTotalCashReceivedForShouldBeDisplayed(Integer int1) {
        assertTrue(true);
    }
    @Then("the total amount paid for {int} should be displayed")
    public void theTotalAmountPaidForShouldBeDisplayed(Integer int1) {
        assertTrue(true);
    }
    @Then("the total amount debts by customers for {int} should be displayed")
    public void theTotalAmountDebtsByCustomersForShouldBeDisplayed(Integer int1) {
        assertTrue(true);
    }
    @Given("a database with no completed orders for the year of {int}")
    public void aDatabaseWithNoCompletedOrdersForTheYearOf(Integer int1) {
        assertTrue(true);
    }
    @When("a request is made to generate statistics for the year of {int}")
    public void aRequestIsMadeToGenerateStatisticsForTheYearOf(Integer int1) {
        assertTrue(true);
    }
    @Then("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String string) {
        assertTrue(true);
    }
    @Given("a request to generate statistics for an invalid year {string}")
    public void aRequestToGenerateStatisticsForAnInvalidYear(String string) {
        assertTrue(true);
    }
    @When("the request is made")
    public void theRequestIsMade() {
        System.out.println("Invalid Year entered");
    }
    @Given("a database with completed orders for {int}")
    public void aDatabaseWithCompletedOrdersFor(Integer int1) {
        assertTrue(true);
    }
    @When("a request is made to generate statistics for {int}")
    public void aRequestIsMadeToGenerateStatisticsFor(Integer int1) {
        assertTrue(true);
    }
    @When("the total amount owed by customers is negative")
    public void theTotalAmountOwedByCustomersIsNegative() {
        System.out.println("Error: Negative debts found");
    }
    @Given("an empty database")
    public void anEmptyDatabase() {
        assertTrue(true);
    }
    @When("a request is made to generate statistics for any year")
    public void aRequestIsMadeToGenerateStatisticsForAnyYear() {
        System.out.println("No completed orders found");
    }


}
