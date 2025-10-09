package kr.goldenmine.baekjoon.bronze.bronze5.p24309;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        BigInteger A = new BigInteger(scan.next());
        BigInteger B = new BigInteger(scan.next());
        BigInteger C = new BigInteger(scan.next());

        System.out.println(B.subtract(C).divide(A));
    }
}
