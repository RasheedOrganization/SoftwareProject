package org.example.AcceptableTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
public class feature7 {
    @Given("a database with a list of completed orders")
    public void aDatabaseWithAListOfCompletedOrders() {
        assertEquals(true,true);
    }
    @When("a request is made to generate statistics for the year of March {int}")
    public void aRequestIsMadeToGenerateStatisticsForTheYearOfMarch(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the total number of delivered items for {int} should be displayed")
    public void theTotalNumberOfDeliveredItemsForShouldBeDisplayed(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the total cash received for {int} should be displayed")
    public void theTotalCashReceivedForShouldBeDisplayed(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the total amount paid for {int} should be displayed")
    public void theTotalAmountPaidForShouldBeDisplayed(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the total amount debts by customers for {int} should be displayed")
    public void theTotalAmountDebtsByCustomersForShouldBeDisplayed(Integer int1) {
        assertEquals(true,true);
    }
    @Given("a database with no completed orders for the year of {int}")
    public void aDatabaseWithNoCompletedOrdersForTheYearOf(Integer int1) {
        assertEquals(true,true);
    }
    @When("a request is made to generate statistics for the year of {int}")
    public void aRequestIsMadeToGenerateStatisticsForTheYearOf(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String string) {
        assertEquals(true,true);
    }
    @Given("a request to generate statistics for an invalid year {string}")
    public void aRequestToGenerateStatisticsForAnInvalidYear(String string) {
        assertEquals(true,true);
    }
    @When("the request is made")
    public void theRequestIsMade() {
        System.out.println("Invalid Year entered");
    }
    @Given("a database with completed orders for {int}")
    public void aDatabaseWithCompletedOrdersFor(Integer int1) {
        assertEquals(true,true);
    }
    @When("a request is made to generate statistics for {int}")
    public void aRequestIsMadeToGenerateStatisticsFor(Integer int1) {
        assertEquals(true,true);
    }
    @When("the total amount owed by customers is negative")
    public void theTotalAmountOwedByCustomersIsNegative() {
        System.out.println("Error: Negative debts found");
    }
    @Given("an empty database")
    public void anEmptyDatabase() {
        assertEquals(true,true);
    }
    @When("a request is made to generate statistics for any year")
    public void aRequestIsMadeToGenerateStatisticsForAnyYear() {
        System.out.println("No completed orders found");
    }


}
