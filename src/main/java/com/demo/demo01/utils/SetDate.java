package com.demo.demo01.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SetDate {

    public static String getNowDate(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }
}
