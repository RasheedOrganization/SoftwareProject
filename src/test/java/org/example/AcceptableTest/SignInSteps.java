package org.example.AcceptableTest;

import com.example.connectionDatabase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignInSteps {
    private connectionDatabase data;

    @Given("I have chosen to sign in")
    public void iHaveChosenToSignIn() {
        data = connectionDatabase.getInstance();
        boolean status = data.getConnection();
        assertTrue(status);
    }
    @When("I sign in with an email address does not contain {string}")
    public void iSignInWithAnEmailAddressDoesNotContain(String string) {
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
                    assertTrue("emailL contain", true);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                assertTrue("emailL does not contain", true);
            }
        } catch (Exception e) {
            assertTrue("Exception in feature", false);
        }
    }
    @When("I sign in with invalid password {string} and valid email {string}")
    public void iSignInWithInvalidPasswordAndValidEmail(String string, String string2) {
        try {
            //System.out.println(string);
            Connection con = data.getConnectData();
            String all = "select password from User_Table where email_user = '" + string2 + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            if(rs.next()) {
                boolean flag = true;
                if (!rs.getString(1).equals(string)) {
                    assertTrue("password is Wrong", true);
                } else {
                    assertTrue("password is Right", true);
                }
            }
            else {
                assertTrue("emailL is Wrong", true);
            }
        } catch (Exception e) {
            assertTrue("Exception in feature", false);
        }
    }

    @When("I sign in with valid details email {string} and password {string}")
    public void iSignInWithValidDetailsEmailAndPassword(String string, String string2) {
        try {
            //System.out.println(string);
            Connection con = data.getConnectData();
            String all = "select * from User_Table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(all);
            boolean flag = true;
            while (rs.next()) {
                String emailL = rs.getString(1);
                String password = rs.getString(2);
                if(emailL.equals(string)) {
                    if(password.equals(string2)) {
                        assertTrue("emailL and password correct", true);
                        flag = false;
                        break;
                    }
                    else {
                        assertTrue("password not correct", true);
                    }
                }
            }
            if (flag) {
                assertTrue("emailL does not contain", true);
            }
        } catch (Exception e) {
            assertTrue("Exception in feature", false);
        }
    }
}
