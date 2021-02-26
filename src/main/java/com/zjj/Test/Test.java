package com.zjj.Test;

import com.zjj.utils.DateUtils;

import java.util.Date;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        //System.out.println(Math.round((Math.random() + new Random().nextInt(9) + 1) * 1000));
        //System.out.println((Math.random() + new Random().nextInt(9) + 1) * 1000);
        Date date = new Date();
        String dateAfter = DateUtils.getDateAfter(date, 14);
        System.out.println(dateAfter);
    }
}
