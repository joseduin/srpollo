package com.patricia.srpollo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Jose on 3/3/2018.
 */

public class Hora {

    private static SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
    private static TimeZone tz = TimeZone.getTimeZone("America/La_Paz");

    public static String horaActual() {
        sdf.setTimeZone(tz);
        return sdf.format(new Date());
    }

}
