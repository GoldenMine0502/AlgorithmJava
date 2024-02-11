package kr.goldenmine.baekjoon.gold.gold1.p1450;

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
        int C = scan.nextInt();

        int[] arr = new int[N + 1];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        int left = 0;
        int right = 0;
        int count = 0;
        int total = 0;

        while(left <= N && right <= N) {
            if(total <= C) {
//                if(primes.get(right) >= N) break;
                total += arr[right++];
            }

            if(total > C) {
                total -= arr[left++];
            }

            if(total <= C) {

            }
//            System.out.println(count + ", " + total + ", " + left + ", " + right);
        }

        System.out.println(count);
    }
}
