package com.giu7.bandb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MyUtils {

    public static DateFormat formatter_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    public static DateFormat formatter_ddMMyyyyHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public static String getSiNo (boolean b){
        if (b) return "Si";
        return "No";
    }
}
