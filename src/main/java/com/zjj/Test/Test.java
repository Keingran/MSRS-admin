package com.zjj.Test;

import com.zjj.utils.DateUtils;

import java.util.Date;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        //System.out.println(Math.round((Math.random() + new Random().nextInt(9) + 1) * 1000));
        //System.out.println((Math.random() + new Random().nextInt(9) + 1) * 1000);
        //Date date = new Date();
        //String dateAfter = DateUtils.getDateAfter(date, 14);
        //System.out.println(dateAfter);
        int[] result = new int[2];
        String limit = "1/60";
        String[] limits = limit.split("/");

        result[0] = Integer.parseInt(limits[0]);
        // 规定时间
        result[1] = Integer.parseInt(limits[1]);
        System.out.println(limits[0]);
        System.out.println(limits[1]);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
