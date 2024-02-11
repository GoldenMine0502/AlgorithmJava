package kr.goldenmine.baekjoon.gold.gold3.p1520F;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //4 5
        //50 45 37 32 30
        //35 50 40 20 25
        //30 30 25 17 28
        //27 24 22 15 10
        Scanner scan = new Scanner(System.in);

        int M = scan.nextInt(); // 세로
        int N = scan.nextInt(); // 가로

        int[][] arr = new int[M][N]; // 가로부터 채운다
        boolean[][] visited = new boolean[M][N];

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        // bfs/dfs가 제일 좋을듯

        int count = solve(arr, visited, M, N,  0, 0);

        System.out.println(count);
    }

    public static int solve(int[][] arr, boolean[][] visited, int M, int N, int m, int n) {
        int c = 0;

        int currentValue = arr[m][n];

        // 위 이동
        if(m > 0) {
            if(currentValue > arr[m - 1][n] && !visited[m - 1][n]) {
                visited[m - 1][n] = true;
                c += solve(arr, visited, M, N, m - 1, n);
                visited[m - 1][n] = false;
            }
        }

        // 아래 이동
        if(m < M - 1) {
            if(currentValue > arr[m + 1][n] && !visited[m + 1][n]) {
                visited[m + 1][n] = true;
                c += solve(arr, visited, M, N, m + 1, n);
                visited[m + 1][n] = false;
            }
        }

        // 왼쪽 이동
        if(n > 0) {
            if(currentValue > arr[m][n - 1] && !visited[m][n - 1]) {
                visited[m][n - 1] = true;
                c += solve(arr, visited, M, N, m, n - 1);
                visited[m][n - 1] = false;
            }
        }

        // 오른쪽 이동
        if(n < N - 1) {
            if(currentValue > arr[m][n + 1] && !visited[m][n + 1]) {
                visited[m][n + 1] = true;
                c += solve(arr, visited, M, N, m, n + 1);
                visited[m][n + 1] = false;
            }
        }

        if(m == M - 1 && n == N - 1) {
            return c + 1;
        } else {
            return c;
        }
    }
}
