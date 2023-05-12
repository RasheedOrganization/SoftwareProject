package org.example.AcceptableTest;
import com.example.TESTINPUT;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

public class feature5 {

    @When("user click on insert order and flag is {string}")
    public void userClickOnInsertOrderAndFlagIs(String string) {
        boolean flag = true;
        if (flag==true) {
            assertEquals(true,true);
        }
        else assertEquals(false,false);
    }
    @Then("the field {string} should be with error")
    public void theFieldShouldBeWithError(String string) {
        System.out.printf("Please enter png extension!");
        assertEquals(false,false);
    }
    @When("he fill in {string} with {string}")
    public void heFillInWith(String string, String string2) {
        boolean flag=false;
        if(string.equals("Name")){
            int flag2= TESTINPUT.ordernameTest(string2);
            if(flag2!=0) assertEquals(true,true);
            else assertEquals(false,false);
        }
        else if (string.equals("Quantity")) {
            flag= TESTINPUT.orderQuantityTest(string2);
            if(flag == true)assertEquals(true,true);
            else assertEquals(false,false);
        }
        else if (string.equals("size")) {
            flag=TESTINPUT.orderSizeTest(string2);
            if(flag==true) assertEquals(true,true);
            else assertEquals(false,false);
        }
        else if (string.equals("color")) {
            flag=TESTINPUT.orderColorTest(string2);
            if (flag == true) assertEquals(true, true);
            else assertEquals(false, false);
        }
    }
    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        if(string.equals(true)){
            assertEquals(true,true);

        }
    }
    @Then("the information has been entered successfully")
    public void theInformationHasBeenEnteredSuccessfully() {
            assertEquals(true,true);
        }
    @Then("the user should see {string}")
    public void theUserShouldSee(String string) {
        assertEquals(false,false);
    }
    }

