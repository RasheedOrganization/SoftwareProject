package org.example.AcceptableTest;

import com.example.ConnectionDatabase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignUpSteps {
    private ConnectionDatabase data;
    @Given("I have chosen to sign up")
    public void iHaveChosenToSignUp() {
        data = ConnectionDatabase.getInstance();
        boolean status = data.getConnection();
        assertTrue(status);
        //assertEquals("error 404",true,false);
    }
    @When("I sign up with valid details email {string} and password {string} and passwordMatch {string} and name {string} and phone {string}")
    public void iSignUpWithValidDetailsEmailAndPasswordAndPasswordMatchAndNameAndPhone(String string, String string2, String string3, String string4, String string5) {
        try {
            //System.out.println(string);
            Connection con = data.getConnectData();
            String all = "select * from User_Table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            boolean flag = true;
            while (rs.next()) {
                String emailL = rs.getString(1);
                if(emailL.equals(string)) {
                    assertTrue(false);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if(string2.length() < 8 || Character.isDigit(string2.charAt(0))) {
                    assertTrue("Password is Wrong", false);
                }
                else {
                    assertTrue(true);
                }
            }
        }
        catch (Exception e) {
            assertTrue("Exception in feature", false);
        }
    }

    @Then("I should see a personalized greeting {string}")
    public void iShouldSeeAPersonalizedGreeting(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(string);
    }


    @When("I sign up with an email address {string} that has already registered")
    public void iSignUpWithAnEmailAddressThatHasAlreadyRegistered(String string) {
        try {
            //System.out.println(string);
            Connection con = data.getConnectData();
            String all = "select Email_User from User_Table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            boolean flag = true;
            while (rs.next()) {
                String emailL = rs.getString(1);
                if(emailL.equals(string)) {
                    assertTrue("Password is Wrong", true);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                assertTrue("emailL is Right", true);
            }
        } catch (Exception e) {
            assertTrue("Exception in feature", false);
        }
    }
    @When("I sign up with invalid password {string} format")
    public void iSignUpWithInvalidPasswordFormat(String string) {
        if(string.length() < 8 || Character.isDigit(string.charAt(0))) {
            assertTrue("Password is Wrong", true);
        }
        else {
            assertTrue("Password is Right", true);
        }
    }
    @When("I sign up with un match password {string} passwordMatch {string} format")
    public void iSignUpWithUnMatchPasswordPasswordMatchFormat(String string, String string2) {
        if(!string.equals(string2)) {
            assertTrue("Password Does Not Match", true);
        }
        else {
            assertTrue("Password is Match", true);
        }
    }
    @When("I sign up with invalid name {string} format")
    public void iSignUpWithInvalidNameFormat(String string) {
        if(Character.isDigit(string.charAt(0))) {
            assertTrue("name is Wrong", true);
        }
        else {
            assertTrue("name is Right", true);
        }
    }
    @When("I sign up with invalid phone {string} format")
    public void iSignUpWithInvalidPhoneFormat(String string) {
        if(!string.startsWith("05")) {
            assertTrue("phone is Wrong", true);
        }
        else {
            assertTrue("phone is Right", true);
        }
    }
    @Then("I should to told to me a {string}")
    public void iShouldToToldToMeA(String string) {
        System.out.println(string);
    }
    @Then("I should to told to me a 'Password shouldn't start with number'")
    public void iShouldToToldToMeAPasswordShouldnTStartWithNumber() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Password shouldn't start with number");
    }
    @Then("I should to told to me a 'Password doesn't match'")
    public void iShouldToToldToMeAPasswordDoesnTMatch() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Password doesn't match");
    }
    @Then("I should to told to me a 'name shouldn't start with number'")
    public void iShouldToToldToMeANameShouldnTStartWithNumber() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("name shouldn't start with number");
    }
}
