package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String s1 = "str";
        int i2 = 32;
        byte b3 = -128;
        short sh4 = 32767;
        long l5 = 2147483647;
        float f6 = 1.4e-45f;
        double d7 = 1.7e+308;
        boolean b8 = true;

        LOG.debug("string : {}, int : {}, byte : {}, short : {}, long : {}, float : {}, double : {}, boolean : {}",
        s1, i2, b3, sh4, l5, f6, d7, b8);
    }
}