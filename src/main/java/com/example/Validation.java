package com.example;

import java.util.regex.Pattern;

public class Validation {
    public static boolean emailPatternMatches(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
    public static boolean passwordPatternMatches(String Password) {
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        return Pattern.compile(regexPattern)
                .matcher(Password)
                .matches();
    }
    public static boolean phonePatternMatches(String Phone) {
        String regexPattern = "^[0-9]{10}$";
        return Pattern.compile(regexPattern)
                .matcher(Phone)
                .matches();
    }
}
