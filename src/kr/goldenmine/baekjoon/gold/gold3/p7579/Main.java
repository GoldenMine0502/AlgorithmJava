package kr.goldenmine.baekjoon.gold.gold3.p7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static class Point {
        int x;
        int y;
//        int count;
//        int max;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
//            this.count = count;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
//                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        final int SIZE = 10000;
        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] memories = new int[N + 1];
        int[] costs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            memories[i] = scan.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            costs[i] = scan.nextInt();
        }

        int[] dp = new int[SIZE + 1];
        for(int i = 0; i <= SIZE; i++) {
            dp[i] = -1;
        }

//        for (int n = 1; n <= N; n++) {
//            int cost = costs[n];
//
//            for (int i = SIZE; i >= cost; i--) {
//
//                if (dp[i - cost] != -1) {
//                    int next = dp[i - cost] + memories[n];
//                    if(next > dp[i]) {
//                        dp[i] = next;
//                    }
//                }
//
//                if(dp[cost] < memories[n]) dp[cost] = memories[n];
//            }
//        }

        for(int i=1; i<=N; i++){
            int cost = costs[i];
            // 뒤에서 부터 확인해야 겹치지 않고 값을 update 할 수 있다.
            for(int j=SIZE; j>=cost; j--){
                if(dp[j-cost] != -1){
                    // 이전 j-cost 까지의 최대 값에 현재 mem을 더하는게 더 크다면 update
                    int next = dp[j-cost] + memories[i];
                    if(next > dp[j])
                        dp[j] = next;
                }
            }
            // 메모리 업데이트가 안되어있는 경우 업데이트
            // 단 메모리가 더 큰 경우에만 업데이트 가능
            if(dp[cost] < memories[i]) dp[cost] = memories[i];
        }

        for(int i=1; i<=SIZE; i++){
            if(dp[i] >= M){
                System.out.println(i);
                break;
            }
        }
//        System.out.println(Arrays.toString(dp));
    }
}
