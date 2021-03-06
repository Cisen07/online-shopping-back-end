package com.demo.mms.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by hkx on 2018/7/15.
 */
public class IDGenerator {

    private static SimpleDateFormat df = new SimpleDateFormat("yyMMddHH");

    public static String getId() {
        return df.format(new Date()) + "-" + UUID.randomUUID().toString().replace("-", "").substring(9);
    }

    public static  int getIdInt9(){
        return (int)((Math.random()*9+1)*100000000);   //生成9位随机数
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

//            System.out.println(IDGenerator.getId());
            System.out.println(IDGenerator.getIdInt9());
        }
    }
}
