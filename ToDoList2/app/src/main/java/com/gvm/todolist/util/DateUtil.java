package com.gvm.todolist.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String formatDate(Date value) {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return simpleDate.format(value);
    }
}
