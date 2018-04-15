package com.example.senura.lipermitest1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by senura on 4/15/18.
 */

public class TimeClass {


    private static Calendar cal =null;


    public static String getTime(){
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

}
