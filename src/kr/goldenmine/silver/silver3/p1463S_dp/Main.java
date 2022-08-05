package kr.goldenmine.silver.silver3.p1463S_dp;

import java.util.Scanner;

public class Main {

    public static void solve(int[] cache, int value, int count) {
        if(cache[value] > 0 && count >= cache[value]) return;

        cache[value] = count;
        if(value == 1) return;

        if(value % 3 == 0) {
            solve(cache, value / 3, count + 1);
        }

        if(value % 2 == 0) {
            solve(cache, value / 2, count + 1);
        }

        if(value > 1) {
            solve(cache, value - 1, count + 1);
        }
    }

    //https://www.acmicpc.net/problem/1463
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int[] cache = new int[1000001];

        solve(cache, N, 0);

        System.out.println(cache[1]);
    }
}
