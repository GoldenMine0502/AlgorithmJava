package kr.goldenmine.gold.gold3.p2879A;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] start = new int[N + 1];
        int[] finish = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            start[i] = scan.nextInt();
        }
        for (int i = 1; i <= N; i++) {
            finish[i] = scan.nextInt();
        }

        // DP[i+1] = DP[i] + max(0, abs(diff[i+1]) - abs(diff[i]))
        int[] dp = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            int diff0 = Math.abs(finish[i] - start[i]);
            int diff1 = Math.abs(finish[i - 1] - start[i - 1]);
            if(diff0 * diff1 > 0) {
                dp[i] = dp[i - 1] + Math.max(0, diff0 - diff1);
            } else {
                dp[i] = dp[i - 1] + diff0;
            }
        }
        System.out.println(dp[N]);

//        int[][] dp = new int[N + 1][N + 1];
//
//        // 최댓값으로 초기화
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                dp[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
//        // 자기부터 자기까지는 단순히 시작부터 끝의 차이가 답임
//        for (int i = 1; i <= N; i++) {
//            dp[i][i] = Math.abs(finish[i] - start[i]);
//        }
//
//
//        // dp[i][j] = i~j까지 최소 인덴트 필요량
//        // dp[i][j] = min(dp[i][j - 1] + 인덴트 최솟값, dp[i][j - 2] + 인덴트 최솟값)
//        for (int len = 1; len <= N; len++) {
//            for (int i = 1; i + len <= N && i < N; i++) {
//                int startIndex = i;
//                int finishIndex = i + len;
//
//                int tabMin = Integer.MAX_VALUE;
//                for(int tabLen = 1; tabLen <= len; tabLen++) {
//                    // 뭉쳐서 탭
////                    for(int index = )
//                    int from = finishIndex - tabLen;
//                    int to = finishIndex;
//
//                    int bothMin = Integer.MAX_VALUE;
//
//                    for(int t = from; t <= to; t++) {
//                        bothMin = Math.min();
//                    }
//                }
//
//                // 남은 부분은 따로 탭 하기
//
//                dp[startIndex][finishIndex] = Math.min(dp[startIndex][finishIndex],
//                                dp[startIndex][finishIndex - 1] + dp[startIndex + len][finishIndex]);
//            }
//        }
//        System.out.println(dp[1][N]);
//        for(int i = 0; i <= N; i++) {
//            for(int j = 0; j <= N; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        // dp[0][n] = 1~n까지 최소 추가 인덴트 필요량
        // dp[0][n] = dp[0][n - 1] + (finish[0][n] - start[0])
        // dp[1][n] = 1~n까지 최소 제거 인덴트 필요량

//        int[][] dp = new int[2][N + 1];
//
//        for(int i = 0; i <= N; i++) {
//            dp[0][i] = Integer.MAX_VALUE;
//            dp[1][i] = Integer.MIN_VALUE;
//        }
//
//        dp[0][0] = 0;
//        dp[1][0] = 0;
//        dp[0][1] = Math.max(0, finish[0] - start[0]);
//        dp[1][1] = Math.min(0, start[0] - finish[0]);
//
//        for(int n = 2; n <= N; n++) {
//            int sub = n - 1;
//            int totalMin = Integer.MAX_VALUE;
//
//            // 과거 전체 순회
//            for(int prev = 1; prev <= n; prev++) {
//                int min = Integer.MAX_VALUE;
//                for(int j = prev; j <= n; j++) {
//                    min = Math.min(min, finish[j - 1] - start[j - 1]);
//                }
//
//                boolean possible = true;
//                for(int j = prev; j <= n; j++) {
//                    if(start[j - 1] + min != finish[j - 1]) {
//                        possible = false;
//                        break;
//                    }
//                }
//
//                // 현재 범위로 다 덮을 수 있으면 저장
//                if(possible) {
//                    totalMin = Math.min(totalMin, min);
//                    sub = prev - 1;
//                }
//                System.out.println(sub + ", " + totalMin + ", " + possible + ", " + min);
//            }
//            System.out.println(n + ": " + sub + ", " + totalMin);
//            dp[0][n] = Math.min(dp[0][n], dp[0][sub] + totalMin);
//        }
//
//        for(int i = 0; i < 2; i++) {
//            for(int j = 0; j <= N; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}
