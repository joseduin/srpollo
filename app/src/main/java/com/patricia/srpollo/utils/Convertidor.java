package com.patricia.srpollo.utils;

import android.util.Log;

/**
 * Created by Jose on 3/3/2018.
 */

public class Convertidor {

    public static int StringToInt(String s) {
        if (s == null || s.equals("")) return 0;
        return Integer.parseInt(s);
    }

    public static double StringToDouble(String s) {
        if (s == null || s.equals("")) return 0.0;
        return Double.parseDouble(s);
    }

    public static String DoubleToString(double d, int decimal) {
        return String.format("%." + decimal + "f", d).replace(",", ".");
    }

    public static String DateEnToEs(String d) {
        String[] s = d.split("-");
        return s[2] + "/" + s[1] + "/" + s[0];
    }

    public static String DateEsToEn(String d) {
        String[] s = d.split("/");
        return s[2] + "-" + s[1] + "-" + s[0];
    }
}
