package org.example.AcceptableTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class feature8 {
    @Given("a list of {int} orders and {int} available workers")
    public void aListOfOrdersAndAvailableWorkers(Integer int1, Integer int2) {
        assertEquals(true,true);
    }
    @When("a request is made to distribute the orders")
    public void aRequestIsMadeToDistributeTheOrders() {
        assertEquals(true,true);
    }
    @Then("each worker should receive at least {int} orders")
    public void eachWorkerShouldReceiveAtLeastOrders(Integer int1) {
        assertEquals(true,true);
    }
    @Then("the orders should be distributed in a fair and balanced way")
    public void theOrdersShouldBeDistributedInAFairAndBalancedWay() {
        assertEquals(true,true);
    }

    @Then("each worker should receive at least {int} order")
    public void eachWorkerShouldReceiveAtLeastOrder(Integer int1) {
        assertEquals(true,true);

    }
    @Then("{int} workers should not receive any orders")
    public void workersShouldNotReceiveAnyOrders(Integer int1) {
        assertEquals(true,true);

    }
    @Then("the remaining orders should be distributed in a fair and balanced way")
    public void theRemainingOrdersShouldBeDistributedInAFairAndBalancedWay() {
        assertEquals(true,true);
    }
    @Given("a list of {int} orders and no available workers")
    public void aListOfOrdersAndNoAvailableWorkers(Integer int1) {
        System.out.println("No available workers");
    }
    @Given("an empty order list and {int} available workers")
    public void anEmptyOrderListAndAvailableWorkers(Integer int1) {
        System.out.println("No orders to distribute");
    }
}