package com.giu7.bandb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    public static DateFormat formatter_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    public static DateFormat formatter_ddMMyyyyHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String getSiNo (boolean b){
        if (b) return "Si";
        return "No";
    }

    public static boolean isPhoneValid(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
