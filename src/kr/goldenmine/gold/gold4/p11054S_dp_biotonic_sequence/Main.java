package kr.goldenmine.gold.gold4.p11054S_dp_biotonic_sequence;

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

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        int[][] dp = new int[N][2];

        for(int i = 0; i < N; i++) {
            int current = arr[i];

            int increasing = 0;

            for (int j = 0; j < i; j++) {
                int previous = arr[j];

                if (current > previous) {
                    increasing = Math.max(increasing, dp[j][0]);
                }
            }

            dp[i][0] = increasing + 1;
        }

        for(int i = N - 1; i >= 0; i--) {
            int current = arr[i];

            int decreasing = 0;

            for (int j = i + 1; j < N; j++) {
                int previous = arr[j];

                if (current > previous) {
                    decreasing = Math.max(decreasing, dp[j][1]);
                }
            }

            dp[i][1] = decreasing + 1;
        }


//        System.out.println(cache[N - 1]);

        int max = -1;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, dp[i][0] + dp[i][1] - 1);
//            System.out.println(arr[i] + ": (" + dp[i][0] + ", " + dp[i][1] + ")");
        }

        System.out.println(max);
    }
}
