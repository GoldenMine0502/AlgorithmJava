package kr.goldenmine.baekjoon.bronze.bronze5.p13277;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        BigInteger A = new BigInteger(scan.next());
        BigInteger B = new BigInteger(scan.next());

        System.out.println(A.multiply(B));
    }
}
