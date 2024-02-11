package kr.goldenmine.baekjoon.platinum.platinum1.p12728F;

import java.math.BigDecimal;
//import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        final int MAX = 100;
        final BigDecimal THREE = new BigDecimal("3");
        final BigDecimal FIVE = new BigDecimal("5");
        final BigDecimal FIVE_SQRT = new BigDecimal(String.valueOf(Math.sqrt(5)));
//        final BigDecimal base = THREE.add(FIVE.sqrt(new MathContext(100, RoundingMode.FLOOR)));
        final BigDecimal base = THREE.add(FIVE_SQRT);

//        System.out.println(base);

        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            int N = scan.nextInt();

            String result = pow(base, N).toBigInteger().toString();
            if(result.length() == 1) {
                System.out.println("Case #" + (i + 1) + ": 00" + result);
            } else if(result.length() == 2) {
                System.out.println("Case #" + (i + 1) + ": 0" + result);
            } else {
                System.out.println("Case #" + (i + 1) + ": " + result.substring(result.length() - 3));
            }
        }
    }

    static BigDecimal mod = new BigDecimal("1000");

    public static BigDecimal pow(BigDecimal base, int exponent) {
//        System.out.println(exponent + "A");
        if(exponent == 1) {
            return base;
        }

        if(exponent == 2) {
            return base.multiply(base);
        }

        BigDecimal half = pow(base, exponent / 2).remainder(mod).setScale(100, RoundingMode.FLOOR);
//        System.out.println(half);

        if(exponent % 2 == 0) {
            return (half.multiply(half)).remainder(mod);
        } else {
            return (half.multiply(half).multiply(base)).remainder(mod);
        }
    }
}
