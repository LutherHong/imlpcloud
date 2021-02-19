package zzh.inspur.tmptest;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class My {
    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("0.00");
        String a1 = "300";
        String a2 = "66.24235";
        String a3 = "11.1";

        System.out.println(format.format(new BigDecimal(a1)));
        System.out.println(format.format(new BigDecimal(a2)));
        System.out.println(format.format(new BigDecimal(a3)));

    }
}
