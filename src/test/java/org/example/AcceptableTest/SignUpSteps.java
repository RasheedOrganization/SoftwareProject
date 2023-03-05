package org.example.AcceptableTest;

import com.example.ConnectionDatabase;
import com.example.HelloApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class SignUpSteps {
    private ConnectionDatabase Data;
    @Given("I have chosen to sign up")
    public void iHaveChosenToSignUp() {
        Data = ConnectionDatabase.getInstance();
        boolean status = false;
        if(Data.getConnection()) status = true;
        assertEquals(true, status);
        //assertEquals("error 404",true,false);
    }
    @When("I sign up with valid details")
    public void iSignUpWithValidDetails() {
        // check if the enterd detalis is true
        System.out.println(1);
        //hello//
    }
    @Then("I should receive a confirmation email")
    public void iShouldReceiveAConfirmationEmail() {

    }
    @Then("I should see a personalized greeting message")
    public void iShouldSeeAPersonalizedGreetingMessage() {

    }


    @When("I sign up with an email address that has already registered")
    public void iSignUpWithAnEmailAddressThatHasAlreadyRegistered() {
        System.out.println(2);
    }
    @Then("I should to told to me that email has already registered")
    public void iShouldToToldToMeThatEmailHasAlreadyRegistered() {

    }
    @Then("I should be offered the option to recover my password.")
    public void iShouldBeOfferedTheOptionToRecoverMyPassword() {

    }

    @When("I sign up with invalid password format")
    public void iSignUpWithInvalidPasswordFormat() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("I should see a guide message for the correct password format")
    public void iShouldSeeAGuideMessageForTheCorrectPasswordFormat() {
        // Write code here that turns the phrase above into concrete actions

    }
}
