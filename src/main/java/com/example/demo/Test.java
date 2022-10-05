package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List map = extracted(31);
        System.out.println(map);

    }

    private static List extracted(int numberDays) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.DATE, -numberDays);
        to.add(Calendar.DATE, +1);

        Date fromD = from.getTime();
        Date toD = to.getTime();

        SimpleDateFormat formTo = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = formTo.format(fromD);
        String format2 = formTo.format(toD);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        return Arrays.asList(format1, format2);
    }
}
