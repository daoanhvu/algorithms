package com.bkda.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bkda.common.Constants;

public final class DataHelper {
	public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
