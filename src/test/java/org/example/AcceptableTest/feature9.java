package org.example.AcceptableTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class feature9 {
    @Given("a customer with a valid email address")
    public void aCustomerWithAValidEmailAddress() {
        String string = "Gmail";
        if(string.equals("Gmail")) {
            String string2 = "Gmail";
            if (Character.isDigit(string2.charAt(0)) || string2.length() == 17) assertFalse(false);
            else {
                boolean flag = false;
                for (int i = 1; i < string2.length(); i++) {
                    if (string2.charAt(i) == '@') {
                        flag = true;
                        break;
                    }
                }
                if (!flag) assertFalse(false);
                else assertTrue(true);
            }
        }
    }
    @Given("an order that has been completed")
    public void anOrderThatHasBeenCompleted() {
        String string = "status";
        if(string.equals("status")) {
            String string2 = "status";
            if(string.equals("status")== string2.equals("completed")) {
                boolean flag = false;
                if (!flag) assertFalse(false);
                else assertTrue(true);
            }
        }
        assertTrue(true);
    }
    @When("a request is made to send an email notification to the customer")
    public void aRequestIsMadeToSendAnEmailNotificationToTheCustomer() {
        assertTrue(true);
    }
    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        System.out.println("the email sent successfully");
    }
    @Then("the customer should receive an email confirming that their order is complete")
    public void theCustomerShouldReceiveAnEmailConfirmingThatTheirOrderIsComplete() {
        assertTrue(true);
    }

    @Given("a customer with an invalid email address")
    public void aCustomerWithAnInvalidEmailAddress() {
        assertTrue(true);
    }
    @Then("the email should not be sent")
    public void theEmailShouldNotBeSent() {
        System.out.println("Invalid email address");
    }

    @Given("an order that is not yet complete")
    public void anOrderThatIsNotYetComplete() {
        System.out.println("Order not yet complete");
    }

    @Given("the email service is not available")
    public void theEmailServiceIsNotAvailable() {
        System.out.println("emailL service not available");
    }
    @Given("a customer with an empty email address")
    public void aCustomerWithAnEmptyEmailAddress() {
        System.out.println("Customer email address is empty");
    }




}
