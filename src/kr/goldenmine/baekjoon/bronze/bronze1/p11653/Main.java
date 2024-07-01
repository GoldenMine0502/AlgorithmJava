package kr.goldenmine.baekjoon.bronze.bronze1.p11653;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random r = new Random();

    public static int rand() {
        return r.nextInt(1 << 16);
    }

    public static int imFeelingLucky(int n) {
        int cnt = 0;
        while (n > 1) {
            cnt++;
//            System.out.println(cnt);
            int _random = rand() * rand() % (n - 1) + 2;
            if (gcd(_random, n) > 1) return gcd(_random, n);
        }
        return 1;
    }

    public static int gcd(int n, int k) {
        while(k != 0) {
            int mod = n % k;
            n = k;
            k = mod;
        }

        return n;
    }

    static BufferedWriter w;

    static void printInt(int n) throws IOException {
        w.append(String.valueOf(n));
        w.newLine();
    }

    public static void factorization(int n) throws IOException {
        while(n > 1) {
            int next = imFeelingLucky(n);
            printInt(next);

            n /= next;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = new Scanner(System.in).nextInt();

        w = new BufferedWriter(new OutputStreamWriter(System.out));
        factorization(N);
        w.flush();
//        System.out.println("result: " + imFeelingLucky(111));
    }
}
