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















    @When("I entered details with valid  shape type'Cube' and phone number'{int}' and name'pillow' and address'Jenin' and '{int}' and'{int}' and''and quantity'{int}'")
    public void iEnteredDetailsWithValidShapeTypeCubeAndPhoneNumberAndNamePillowAndAddressJeninAndAndAndAndQuantity(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should see a message expressing validation'Successful Insertion'")
    public void iShouldSeeAMessageExpressingValidationSuccessfulInsertion() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
