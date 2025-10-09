package kr.goldenmine.baekjoon.bronze.bronze5.p8437;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigInteger A = new BigInteger(scan.next());
        BigInteger B = new BigInteger(scan.next());

        // x - y = B
        // x + y = A
        // 2x = A + B

        // 2y = A - B

        BigInteger res = A.add(B).divide(new BigInteger("2"));
        BigInteger res2 = A.subtract(B).divide(new BigInteger("2"));

        System.out.println(res);
        System.out.println(res2);
    }
}
