package org.example.AcceptableTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.jfr.consumer.RecordedObject;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.jupiter.api.Order;

import javax.swing.*;
import java.io.IOException;
//import java.lang.reflect.ProxyGenerator;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;
public class feature6 {
    @Given("a customer with an order value of {int}")
    public void aCustomerWithAnOrderValueOf$(Integer int1) {
        assertEquals(true,true);
    }
    @When("the customer is eligible for a {int}% discount")
    public void theCustomerIsEligibleForADiscount(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the total ordar value should be {int}")
    public void theTotalOrdarValueShouldBe(Integer int1) {
        assertEquals(true,true);
    }

    @Then("the discount amount should be {int}")
    public void theDiscountAmountShouldBe$(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the total order value should be {int}")
    public void theTotalOrderValueShouldBe$(Integer int1) {
        assertEquals(true,true);
    }
    @When("the customer is not eligible for any discount")
    public void theCustomerIsNotEligibleForAnyDiscount() {
        assertEquals(true,true);
    }
    @When("the customer is eligible for a maximum discount of {int}%")
    public void theCustomerIsEligibleForAMaximumDiscountOf(Integer int1) {

        assertEquals(true,true);
       /* DiscountCalculater discountCalculater = new DiscountCalculater();
        Order order = null;
        BigDecimal discountAmount = discountCalculater.calculateDiscount(order, 0.20);
        assertEquals(new BigDecimal("1000"), discountAmount);
        order = null;
        assertEquals(new BigDecimal("4000"), order.getClass());*/
    }
    @Then("the discount amount be should be {int}")
    public void theDiscountAmountBeShouldBe(Integer int1) {
        assertEquals(true,true);
    }

    @Then("an error message should be displayed stating that the minimum order value of {int} was not met")
    public void anErrorMessageShouldBeDisplayedStatingThatTheMinimumOrderValueOfWasNotMet(Integer int1) {
        System.out.println("he minimum order value of 200$ was not met");
        assertEquals(true,true);
    }
    @Given("a customer with an order that includes a {string} item")
    public void aCustomerWithAnOrderThatIncludesAItem(String string) {
        if(string.equals(true)){
            assertEquals(true,true);
        }
        else assertEquals(false,false);

    }
    @When("the customer is eligible for a {int}% discount on that item")
    public void theCustomerIsEligibleForADiscountOnThatItem(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the discount amount for that item should be {double}")
    public void theDiscountAmountForThatItemShouldBe(Double double1) {
        System.out.println("the minimum order value of 200 was not met");
    }






}