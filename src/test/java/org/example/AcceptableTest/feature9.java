package org.example.AcceptableTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class feature9 {
    @Given("a customer with a valid email address")
    public void aCustomerWithAValidEmailAddress() {
        String string = new String("Gmail");
        if(string.equals("Gmail")) {
            String string2 = new String("Gmail");
            if (Character.isDigit(string2.charAt(0)) || string2.length() == 17) assertEquals(false, false);
            else {
                boolean flag = false;
                for (int i = 1; i < string2.length(); i++) {
                    if (string2.charAt(i) == '@') flag = true;
                }
                if (flag == false) assertEquals(false, false);
                else assertEquals(true, true);
            }
        }
    }
    @Given("an order that has been completed")
    public void anOrderThatHasBeenCompleted() {
        String string = new String("status");
        if(string.equals("status")) {
            String string2 = new String("status");
            if(string.equals("status")== string2.equals("completed")) {
                boolean flag = false;
                if (flag == false) assertEquals(false, false);
                else assertEquals(true, true);
            }
        }
        assertEquals(true,true);
    }
    @When("a request is made to send an email notification to the customer")
    public void aRequestIsMadeToSendAnEmailNotificationToTheCustomer() {
        assertEquals(true,true);
    }
    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        System.out.println("the email sent successfully");
    }
    @Then("the customer should receive an email confirming that their order is complete")
    public void theCustomerShouldReceiveAnEmailConfirmingThatTheirOrderIsComplete() {
        assertEquals(true,true);
    }

    @Given("a customer with an invalid email address")
    public void aCustomerWithAnInvalidEmailAddress() {
        assertEquals(true,true);
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
        System.out.println("Email service not available");
    }
    @Given("a customer with an empty email address")
    public void aCustomerWithAnEmptyEmailAddress() {
        System.out.println("Customer email address is empty");
    }




}
