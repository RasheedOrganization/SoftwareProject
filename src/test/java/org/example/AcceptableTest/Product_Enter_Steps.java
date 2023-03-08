package org.example.AcceptableTest;

import com.example.ConnectionDatabase;
import com.example.HelloApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class Product_Enter_Steps {
    private ConnectionDatabase Data;

    @Given("I filled the product details")
    public void i_filled_the_product_details() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I entered details with valid  phone number {string} and name {string} and address {string} and quantity {string} and area {string}")
    public void i_entered_details_with_valid_phone_number_and_name_and_address_and_quantity_and_area(String string, String string2, String string3, String string4, String string5) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should see a message expressing validation {string}")
    public void i_should_see_a_message_expressing_validation(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an invalid Phone number {string} format")
    public void i_enter_an_invalid_phone_number_format(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an invalid name {string} format")
    public void i_enter_an_invalid_name_format(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an empty {string} Address")
    public void i_enter_an_empty_address(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an empty {string} Area")
    public void i_enter_an_empty_area(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an empty {string} Quantity")
    public void i_enter_an_empty_quantity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an invalid Quantity {string} format")
    public void i_enter_an_invalid_quantity_format(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I enter an invalid Area {string} format")
    public void i_enter_an_invalid_area_format(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should show a warning message {string}")
    public void i_should_show_a_warning_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
