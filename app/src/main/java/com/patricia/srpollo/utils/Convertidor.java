package com.patricia.srpollo.utils;

/**
 * Created by Jose on 3/3/2018.
 */

public class Convertidor {

    public static int StringToInt(String s) {
        if (s == null) return 0;
        return Integer.parseInt(s);
    }

    public static double StringToDouble(String s) {
        if (s == null) return 0.0;
        return Double.parseDouble(s);
    }
}
