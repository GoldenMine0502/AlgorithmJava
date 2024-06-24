package kr.goldenmine.baekjoon.silver.silver3.p20116;

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
        double W = scan.nextInt();

        int[] sum = new int[N + 1];
        int[] min = new int[N + 1];
        int[] max = new int[N + 1];
        min[0] = 999999999;
        max[0] = -999999999;
        boolean verify = true;
        for(int i = 1; i <= N; i++) {
            int v = scan.nextInt();
            sum[i] = sum[i - 1] + v;
            min[i] = Math.min(min[i - 1], v);
            max[i] = Math.max(max[i - 1], v);
            double currentAvg = (double) sum[i] / i; // 무게 중심

            if(currentAvg - W / 2 < min[i] - W / 2) verify=false;
            if(currentAvg + W / 2 > max[i] + W / 2) verify=false;

            System.out.println((currentAvg - W / 2) + ", " + min[i] + ", " + (currentAvg + W / 2) + ", " + max[i] + ", " + sum[i] + ", " + verify);
        }

        System.out.println(verify ? "stable" : "unstable");


    }
}
