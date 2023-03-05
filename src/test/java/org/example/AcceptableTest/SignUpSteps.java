package org.example.AcceptableTest;

import com.example.HelloApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpSteps {
    @Given("I have chosen to sign up")
    public void iHaveChosenToSignUp() {
        System.out.println("Hello");
    }
    @When("I sign up with valid details")
    public void iSignUpWithValidDetails() {

    }
    @Then("I should receive a confirmation email")
    public void iShouldReceiveAConfirmationEmail() {

    }
    @Then("I should see a personalized greeting message")
    public void iShouldSeeAPersonalizedGreetingMessage() {

    }


    @When("I sign up with an email address that has already registered")
    public void iSignUpWithAnEmailAddressThatHasAlreadyRegistered() {

    }
    @Then("I should to told to me that email has already registered")
    public void iShouldToToldToMeThatEmailHasAlreadyRegistered() {

    }
    @Then("I should be offered the option to recover my password.")
    public void iShouldBeOfferedTheOptionToRecoverMyPassword() {

    }
}
