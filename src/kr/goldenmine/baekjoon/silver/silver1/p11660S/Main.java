package kr.goldenmine.baekjoon.silver.silver1.p11660S;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] arr = new int[N + 1][N + 1];
        int[][] sumVertical = new int[N + 1][N + 1]; // -> [0][0]부터 [y][x]까지의 합 (세로만)
        int[][] sumHorizontal = new int[N + 1][N + 1]; // -> [0][0]부터 [y][x]까지의 합 (가로만)
        int[][] sumTotal = new int[N + 1][N + 1]; // -> [0][0]부터 [y][x]까지의 합 (전체)

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                sumVertical[y][x] = sumVertical[y - 1][x] + arr[y][x];
            }
        }

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                sumHorizontal[y][x] = sumHorizontal[y][x - 1] + arr[y][x];
            }
        }

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                int a = sumTotal[y - 1][x - 1];
                int b = sumVertical[y - 1][x];
                int c = sumHorizontal[y][x - 1];
                int d = arr[y][x];
                sumTotal[y][x] = a + b + c + d;
            }
        }

//        for(int y = 0; y <= N; y++) {
//            for(int x = 0; x <= N; x++) {
//                System.out.print(sum[y][x] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        for(int y = 0; y <= N; y++) {
//            for(int x = 0; x <= N; x++) {
//                System.out.print(sum2[y][x] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        // sum3 = (1,1) 부터 (x, y) 까지의 합
//        for(int y = 0; y <= N; y++) {
//            for(int x = 0; x <= N; x++) {
//                System.out.print(sum3[y][x] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();

        // (x1, y1), (x2, y2)의 합을 구하려면
        // sum3[y2][x2] - sum3[y1 - 1][x2] - sum3[y2][x1 - 1] + sum3[y1 - 1][x1 - 1]

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < M; i++) {
            int y1 = scan.nextInt();
            int x1 = scan.nextInt();
            int y2 = scan.nextInt();
            int x2 = scan.nextInt();

            int sum = sumTotal[y2][x2] - sumTotal[y1 - 1][x2] - sumTotal[y2][x1 - 1] + sumTotal[y1 - 1][x1 - 1];
            output.write(String.valueOf(sum));
            output.newLine();
        }

        output.flush();
    }
}
