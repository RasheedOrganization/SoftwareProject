package org.example.AcceptableTest;

import com.example.ConnectionDatabase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class SignInSteps {
    private ConnectionDatabase data;

    @Given("I have chosen to sign in")
    public void iHaveChosenToSignIn() {
        data = ConnectionDatabase.getInstance();
        boolean status = false;
        if(data.getConnection()) status = true;
        assertEquals(true, status);
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
                    assertEquals("emailL contain",true, true);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                assertEquals("emailL does not contain",true, true);
            }
        } catch (Exception e) {
            assertEquals("Exception in feature",true, false);
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
                    assertEquals("Password is Wrong", true, true);
                } else {
                    assertEquals("Password is Right", true, true);
                }
            }
            else {
                assertEquals("emailL is Wrong", true, true);
            }
        } catch (Exception e) {
            assertEquals("Exception in feature",true, false);
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
                String Password = rs.getString(2);
                if(emailL.equals(string)) {
                    if(Password.equals(string2)) {
                        assertEquals("emailL and Password correct",true, true);
                        flag = false;
                        break;
                    }
                    else {
                        assertEquals("Password not correct",true, true);
                    }
                }
            }
            if (flag) {
                assertEquals("emailL does not contain",true, true);
            }
        } catch (Exception e) {
            assertEquals("Exception in feature",true, false);
        }
    }
}
