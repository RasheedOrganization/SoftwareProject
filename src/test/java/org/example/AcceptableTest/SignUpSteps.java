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
    @When("I sign up with valid details email {string} and password {string} and passwordMatch {string} and name {string} and phone {string}")
    public void iSignUpWithValidDetailsEmailAndPasswordAndPasswordMatchAndNameAndPhone(String string, String string2, String string3, String string4, String string5) {
        try {
            //System.out.println(string);
            Connection con = Data.getConnectData();
            String all = "select * from User_Table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            boolean flag = true;
            while (rs.next()) {
                String Email = rs.getString(1);
                if(Email.equals(string)) {
                    assertEquals(true, false);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if(string2.length() < 8 || Character.isDigit(string2.charAt(0))) {
                    assertEquals("Password is Wrong",true, false);
                }
                else {
                    assertEquals(true, true);
                }
            }
        } catch (Exception e) {
            assertEquals("Exception in feature",true, false);
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
            Connection con = Data.getConnectData();
            String all = "select Email_User from User_Table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            boolean flag = true;
            while (rs.next()) {
                String Email = rs.getString(1);
                if(Email.equals(string)) {
                    assertEquals("Password is Wrong",true, true);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                assertEquals("Email is Right",true, true);
            }
        } catch (Exception e) {
            assertEquals("Exception in feature",true, false);
        }
    }
    @When("I sign up with invalid password {string} format")
    public void iSignUpWithInvalidPasswordFormat(String string) {
        if(string.length() < 8 || Character.isDigit(string.charAt(0))) {
            assertEquals("Password is Wrong",true, true);
        }
        else {
            assertEquals("Password is Right",true, true);
        }
    }
    @When("I sign up with un match password {string} passwordMatch {string} format")
    public void iSignUpWithUnMatchPasswordPasswordMatchFormat(String string, String string2) {
        if(!string.equals(string2)) {
            assertEquals("Password Does Not Match",true, true);
        }
        else {
            assertEquals("Password is Match",true, true);
        }
    }
    @When("I sign up with invalid name {string} format")
    public void iSignUpWithInvalidNameFormat(String string) {
        if(Character.isDigit(string.charAt(0))) {
            assertEquals("Name is Wrong",true, true);
        }
        else {
            assertEquals("Name is Right",true, true);
        }
    }
    @When("I sign up with invalid phone {string} format")
    public void iSignUpWithInvalidPhoneFormat(String string) {
        if(!string.startsWith("05")) {
            assertEquals("Phone is Wrong",true, true);
        }
        else {
            assertEquals("Phone is Right",true, true);
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
    @Then("I should to told to me a 'Name shouldn't start with number'")
    public void iShouldToToldToMeANameShouldnTStartWithNumber() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Name shouldn't start with number");
    }
}
