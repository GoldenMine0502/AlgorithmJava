package kr.goldenmine.baekjoon.bronze.bronze2.p2920S;

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

        int[] arr = new int[8];

        int ascending = 0;
        int descending = 0;
        for(int i = 0; i < 8; i++) {
            arr[i] = scan.nextInt();
            if(i > 0) {
                if(arr[i] - arr[i - 1] > 0) ascending++;
                if(arr[i] - arr[i - 1] < 0) descending++;
            }
        }

        if(ascending == 7) {
            System.out.println("ascending");
        } else if(descending == 7) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}
