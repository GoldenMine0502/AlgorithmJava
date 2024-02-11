package kr.goldenmine.baekjoon.silver.silver5.p1676A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        int N = scan.nextInt();

        for(int i = 1; i <= 500; i++) {
            int N = i;
            int count = 0;

            while (N >= 5) {
                count += N / 5;
                N /= 5;
            }

            System.out.println(i + ": " + count);
        }
//        BigInteger sum = BigInteger.ONE;
////        int total = 0;
//
//        for(int i = 1; i <= N; i++) {
//            sum = sum.multiply(new BigInteger(String.valueOf(i)));
//
//            BigInteger copy = sum;
//            int count = 0;
////            BigInteger digits = new BigInteger("100000000000000000000000000000000000000000000000000000000000000000000");
//            while(copy.mod(new BigInteger("10")).compareTo(BigInteger.ZERO) == 0) {
//                count++;
//                copy = copy.divide(new BigInteger("10"));
////                digits = digits.multiply(new BigInteger("10"));
////                System.out.println(copy);
//            }
//
////            total += count;
//
////            BigInteger next = sum.mod(digits);
////            System.out.println(i + ": " + sum + ", " + count + ", " + next);
//
//            if(i == N) {
//                System.out.println(count);
//            }
//
////            sum = next;
//        }
    }
}
