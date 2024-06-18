package kr.goldenmine.baekjoon.gold.gold3.p2342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        List<Integer> list = new ArrayList<>();

        while(true) {
            int input = scan.nextInt();
            if(input == 0) break;
            list.add(input);
        }

        int N = list.size();
        final int MAX = 999999999;

//        int[][] dp = new int[2][N + 1]; // 01234 = 왼발, 56789 = 오른발
//        for(int i = 0; i <= N; i++) {
//            for(int j = 0; j < 2; j++) {
//                dp[j][i] = MAX;
//            }
//        }
//        // 1 2 2 4 0
//        dp[0][0] = 0;
//        dp[1][0] = 0;
//
//        for(int i = 1; i <= N; i++) {
//            int current = list.get(i - 1);
//                int leftPower = dp[0][i - 1] + getPower(, current);
//                int rightPower = dp[1][i - 1] + getPower(j, current);
//
//                System.out.println(i + ": " + leftPower + ", " + rightPower);
////                dp[current][i] = Math.min(dp[current][i], leftPower);
////                dp[current + 5][i] = Math.min(dp[current + 5][i], rightPower);
//
//                if(leftPower < rightPower) {
//                    dp[0][i] = Math.min(dp[0][i], leftPower);
//                    dp[1][i] = dp[1][i - 1];
//                } else if(leftPower > rightPower) {
//                    dp[0][i] = dp[current][i - 1];
//                    dp[1][i] = Math.min(dp[1][i], rightPower);
//                } else {
//                    dp[0][i] = Math.min(dp[0][i], leftPower);
//                    dp[1][i] = Math.min(dp[1][i], rightPower);
//                }
//        }
//        for(int i = 0; i < 10; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
        int[][][] dp = new int[5][5][N + 1];

        for(int i = 0; i <= N; i++) {

            for(int left = 0; left <= 4; left++) {
                for (int right = 0; right <= 4; right++) {
                    dp[left][right][i] = MAX;
                }
            }
        }
        dp[0][0][0] = 0;

        for(int i = 1; i <= N; i++) {
            int current = list.get(i - 1);
            for(int left = 0; left <= 4; left++) {
                int powerLeft = getPower(left, current);

                for(int right = 0; right <= 4; right++) {
                    int powerRight = getPower(right, current);

                    // 왼발 고정, 오른발 움직임
                    // 이전 위치가 left, right이니 그 이전 위치에서 오른발 움직일때 파워를 더함
                    dp[left][current][i] = Math.min(dp[left][current][i], dp[left][right][i - 1] + powerRight);

                    // 왼발 움직임, 오른발 고정
                    dp[current][right][i] = Math.min(dp[current][right][i], dp[left][right][i - 1] + powerLeft);
                }
            }
        }

//        for(int i = 1; i <= N; i++) {
//
//            for(int left = 0; left <= 4; left++) {
//                for (int right = 0; right <= 4; right++) {
//                    System.out.print(dp[left][right][i] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        int min = MAX;
        for(int left = 0; left <= 4; left++) {
            for (int right = 0; right <= 4; right++) {
                min = Math.min(min, dp[left][right][N]);
            }
        }
        System.out.println(min);
    }

    public static int getPower(int previous, int current) {
        if(previous >= 10) { // 판단을 보류한 경우
            previous = previous % 10;
        }

        if(previous == 0) { // 이전 발이 가운데면 무조건 2
            return 2;
        }

        int diff = Math.abs(current - previous);



        if(diff == 0) { // 같은 지점을 한번 더 누른다면, 그때는 1의 힘을 사용
            return 1;
        } else if(diff == 1 || diff == 3) { // 다른 지점에서 인접한 지점으로 움직일 때는 3의 힘을 사용
            return 3;
        } else { // if diff == 2 반대편으로 움직일때는 4의 힘을 사용
            return 4;
        }
    }
}
