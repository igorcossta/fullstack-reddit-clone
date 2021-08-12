package com.reddit.spring.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean testPassword(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
