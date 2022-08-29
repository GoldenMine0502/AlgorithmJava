package kr.goldenmine.silver.silver1.p12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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

        int[] dp = new int[N + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while(!queue.isEmpty()) {
            int value = queue.poll();

            if(value == 1) {
                break;
            }

            if(value % 3 == 0 && dp[value / 3] == 0) {
                queue.add(value / 3);
                dp[value / 3] = value;
            }
            if(value % 2 == 0 && dp[value / 2] == 0) {
                queue.add(value / 2);
                dp[value / 2] = value;
            }
            if(dp[value - 1] == 0) {
                queue.add(value - 1);
                dp[value - 1] = value;
            }
        }

        int[] results = new int[N + 1];

        int next = 1;
        int index = 0;
        while(next < N) {
            results[index++] = dp[next];

            next = dp[next];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(index);
        sb.append("\n");

        for(int i = index - 1; i >= 0; i--) {
            sb.append(results[i]);
            sb.append(" ");
        }

        sb.append(1);
        System.out.println(sb);

//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(results));
    }
}
