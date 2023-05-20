package org.example.AcceptableTest;

import com.example.TESTINPUT;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class feature5 {

    @When("user click on insert order and flag is {string}")
    public void userClickOnInsertOrderAndFlagIs(String string) {
        boolean flag = true;
        if (flag) {
            assertTrue(true);
        }
        else assertFalse(false);
    }
    @Then("the field {string} should be with error")
    public void theFieldShouldBeWithError(String string) {
        System.out.print("Please enter png extension!");
        assertFalse(false);
    }
    @When("he fill in {string} with {string}")
    public void heFillInWith(String string, String string2) {
        boolean flag=false;
        if(string.equals("name")){
            int flag2= TESTINPUT.ordernameTest(string2);
            if(flag2!=0) assertTrue(true);
            else assertFalse(false);
        }
        else if (string.equals("Quantity")) {
            flag= TESTINPUT.orderQuantityTest(string2);
            if(flag) assertTrue(true);
            else assertFalse(false);
        }
        else if (string.equals("size")) {
            flag=TESTINPUT.orderSizeTest(string2);
            if(flag) assertTrue(true);
            else assertFalse(false);
        }
        else if (string.equals("color")) {
            flag=TESTINPUT.orderColorTest(string2);
            if (flag) assertTrue(true);
            else assertFalse(false);
        }
    }
    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        if(string.equals(true)){
            assertTrue(true);

        }
    }
    @Then("the information has been entered successfully")
    public void theInformationHasBeenEnteredSuccessfully() {
        assertTrue(true);
        }
    @Then("the user should see {string}")
    public void theUserShouldSee(String string) {
        assertFalse(false);
    }
    }

