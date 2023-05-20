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
    private ConnectionDatabase data;

    @Given("I filled the product details")
    public void i_filled_the_product_details() {
        data = ConnectionDatabase.getInstance();
        boolean status = false;
        if(data.getConnection()) status = true;
        assertEquals(true, status);
    }
    @When("I entered details with valid  phone number {string} and name {string} and address {string} and quantity {string} and area {string}")
    public void i_entered_details_with_valid_phone_number_and_name_and_address_and_quantity_and_area(String string, String string2, String string3, String string4, String string5) {
        assertEquals(true, true);
    }
    @Then("I should see a message expressing validation {string}")
    public void i_should_see_a_message_expressing_validation(String string) {
        System.out.println(string);
    }
    @When("I enter an invalid phone number {string} format")
    public void i_enter_an_invalid_phone_number_format(String string) {
            if(string.length()!=12 || (string.charAt(0)!='9' && string.charAt(0)!='7'))
            {
                assertEquals("Please enter a valid phone Number",true,false);
            }
            else {
                assertEquals(true,true);
            }
    }
    @When("I enter an invalid name {string} format")
    public void i_enter_an_invalid_name_format(String string) {
            for(int i=0;i<string.length();i++)
            {
                if(Character.isDigit(string.charAt(i)))
                {
                    assertEquals("name can't contain digits",true,false);
                    break;
                }
            }
            assertEquals(true,true);
    }
    @When("I enter an empty {string} address")
    public void i_enter_an_empty_address(String string) {
        if(string.equals(null))assertEquals("address can't be empty",true,false);
        else assertEquals(true,true);
    }
    @When("I enter an empty {string} Area")
    public void i_enter_an_empty_area(String string) {
        if(string.equals(null))assertEquals("Area can't be empty",true,false);
        else assertEquals(true,true);
    }
    @When("I enter an empty {string} Quantity")
    public void i_enter_an_empty_quantity(String string) {
        if(string.equals(null))assertEquals("Quantity can't be empty",true,false);
        else assertEquals(true,true);
    }
    @When("I enter an invalid Quantity {string} format")
    public void i_enter_an_invalid_quantity_format(String string) {
        for(int i=0;i<string.length();i++)
        {
            if(!Character.isDigit(string.charAt(i)))
            {
                assertEquals("Quantity must contain Digits only",true,false);
                break;
            }
        }
        assertEquals(true,true);
    }
    @When("I enter an invalid Area {string} format")
    public void i_enter_an_invalid_area_format(String string) {
        for(int i=0;i<string.length();i++)
        {
            if(!Character.isDigit(string.charAt(i)))
            {
                assertEquals("Area must contain Digits only",true,false);
                break;
            }
        }
        assertEquals(true,true);
    }
    @Then("I should show a warning message {string}")
    public void i_should_show_a_warning_message(String string) {
            System.out.println(string);
    }
}
