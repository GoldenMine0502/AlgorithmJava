package kr.goldenmine.baekjoon.platinum.platinum1.p12728F;

import java.math.BigDecimal;
//import java.math.MathContext;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    private static final BigDecimal SQRT_DIG = new BigDecimal(150);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

    /**
     * Private utility method used to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    /**
     * Uses Newton Raphson to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    public static BigDecimal bigSqrt(BigDecimal c){
        return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
    }

    public static void main(String[] args) {
//        final int MAX = 100;
        final BigDecimal THREE = new BigDecimal("3");
        final BigDecimal FIVE = new BigDecimal("5");
        final BigDecimal FIVE_SQRT = bigSqrt(FIVE);
//        final BigDecimal base = THREE.add(FIVE.sqrt(new MathContext(100, RoundingMode.FLOOR)));
        final BigDecimal base = THREE.add(FIVE_SQRT);

//        System.out.println(base);

        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            int N = scan.nextInt();

            String result = pow(base, N).remainder(mod, MathContext.UNLIMITED).toBigInteger().toString();
            System.out.println(result);
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
            return base.multiply(base, MathContext.UNLIMITED);
        }

        BigDecimal half = pow(base, exponent / 2).setScale(50, RoundingMode.HALF_UP);

//        if(half.compareTo(mod) > 0) {
//            half = half.remainder(mod, MathContext.UNLIMITED);
//            System.out.println("d" + exponent + ", " + half);
//        }

        BigDecimal full = half.multiply(half, MathContext.UNLIMITED);
        if(exponent % 2 == 1) {
            full = full.multiply(base, MathContext.UNLIMITED);
        }
        System.out.println(exponent + ", " + full);
//        if(full.compareTo(mod) > 0) {
//            full = full.remainder(mod, MathContext.UNLIMITED);
//            System.out.println("d" + exponent + ", " + full);
//        }

        return full;
    }
}
