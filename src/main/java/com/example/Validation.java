package com.example;

import java.util.regex.Pattern;

public class Validation {
    public static boolean emailPatternMatches(String emailAddress) {
        String regexPattern = "^(.+)@(.+)$";
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
    public static boolean phonePatternMatches(String phone) {
        String regexPattern = "^[0-9]{10}$";
        return Pattern.compile(regexPattern)
                .matcher(phone)
                .matches();
    }
}
