package kr.goldenmine.baekjoon.silver.silver4.p1459;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long X = scan.nextInt();
        long Y = scan.nextInt();
        long W = scan.nextInt(); // 한 블록
        long S = scan.nextInt(); // 대각선

        long d;
        if (2 * W <= S) { // 대각선 이동이 느리다
            d = Math.min(X, Y) * W * 2;
        } else {
            d = Math.min(X, Y) * S;
        } // 여기까지 X, Y = (min(X, Y), min(X, Y)) 에 도착. 4,2인 경우 2,2까지 도착

        long remain = Math.max(X, Y) - Math.min(X, Y);
        if (remain % 2 == 0) { // 대각선 이동도 괜찮
            d += remain * Math.min(W, S);
        } else { // 이땐 한 번은 대각선 이동 불가
            d += (remain - 1) * Math.min(W, S) + W;
        }
        System.out.println(d);
    }
}
