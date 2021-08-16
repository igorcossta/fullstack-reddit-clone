package com.reddit.spring.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private static final String STRING_PATTERN = "^[a-zA-Z]{5,30}$";
    private static final Pattern pattern = Pattern.compile(STRING_PATTERN);

    public static boolean onlyChar(String string) {
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
