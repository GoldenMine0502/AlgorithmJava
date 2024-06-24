package kr.goldenmine.baekjoon.gold.gold1.p1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        String text1 = scan.nextLine();
        String text2 = scan.nextLine();

        int[][] dp = new int[text2.length() + 1][text1.length() + 1];

        // bb
        // abb

        for(int y = 1; y <= text2.length(); y++) {
            char ch2 = text2.charAt(y - 1);
            for(int x = 1; x <= text1.length(); x++) {
                char ch = text1.charAt(x - 1);

                int previousX1 = dp[y][x - 1]; // 음수이면 바로 앞에서 바뀐 적 있다.
                boolean recentlyX1 = previousX1 < 0;
                previousX1 = Math.abs(previousX1);

                int previousXY1 = dp[y - 1][x - 1];
                boolean recentlyXY1 = previousXY1 < 0;
                previousXY1 = Math.abs(previousXY1);


                int previousY1 = dp[y - 1][x];
                boolean recentlyY1 = previousY1 < 0;
                previousY1 = Math.abs(previousY1);

                if(ch == ch2) { // 같으니 변화 없음
                    dp[y][x] = Math.min(previousX1, Math.min(previousY1, previousXY1));
                } else {
                    if(previousX1 < previousXY1) {
                        if(previousX1 < previousY1) { // X값이 제일 작은 경우
                            if(!recentlyX1) {
                                dp[y][x] = -(previousX1 + 1);
                            } else {
                                dp[y][x] = previousX1;
                            }
                        } else { // Y값이 제일 작은 경우
                            if(!recentlyY1) {
                                dp[y][x] = -(previousY1 + 1);
                            } else {
                                dp[y][x] = previousY1;
                            }
                        }
                    } else { // X > XY
                        if(previousXY1 < previousY1) { // XY가 제일 작은 경우
                            if(!recentlyXY1) {
                                dp[y][x] = -(previousXY1 + 1);
                            } else {
                                dp[y][x] = previousXY1;
                            }
                        } else { // Y가 제일 작은 경우
                            if(!recentlyY1) {
                                dp[y][x] = -(previousY1 + 1);
                            } else {
                                dp[y][x] = previousY1;
                            }
                        }
                    }
                }
            }
        }

        for(int y = 0; y <= text2.length(); y++) {
            for(int x = 0; x <= text1.length(); x++) {
                System.out.print(dp[y][x] + " ");
            }
            System.out.println();
        }
    }
}
