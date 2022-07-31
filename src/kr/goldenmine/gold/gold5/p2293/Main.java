package kr.goldenmine.gold.gold5.p2293;

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

    public static int solve(int[] arr, int[][] cache, int current, int k) {
        System.out.println(current);
        if(current > k) return 0;
        if(current == k) return 1;

        int sum = 0;

        for(int i = 0; i < arr.length; i++) {
            sum += solve(arr, cache, current + arr[i], k);
        }

        return sum;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int n = scan.nextInt();
        int k = scan.nextInt();

        int[] arr = new int[n];
        int[][] cache = new int[n][2];
        for(int i = 0 ; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        int result = solve(arr, cache, 0, k);

        System.out.println(result);
    }
}
