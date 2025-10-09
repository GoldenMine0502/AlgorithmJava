package kr.goldenmine.baekjoon.silver.silver3.p14607;

import java.util.Scanner;

public class Main {
    static long dfs(long n) {
        if(n <= 1) return 0;
        if(n == 2) return 1;

        if(n % 2 == 0) {
            // 4라면 2 * 2
            long n1 = n / 2;
//            long n2 = n / 2;
//            return n1 * n2 + dfs(n1) + dfs(n2);
            return n1 * n1 + 2 * dfs(n1);
        } else {
            // 3이라면 2 * 1
            // 5라면 3 * 2
            long n1 = n / 2 + 1;
            long n2 = n / 2;
            return n1 * n2 + dfs(n1) + dfs(n2);
        }
    }
    public static void main(String[] args) {
//        System.out.println(dfs(1));
//        System.out.println(dfs(3));
//        System.out.println(dfs(5));
//        System.out.println(dfs(8));
//        System.out.println(dfs(1_000_000_000L));
        System.out.println(dfs((new Scanner(System.in)).nextLong()));
    }
}
