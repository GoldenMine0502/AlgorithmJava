package kr.goldenmine.baekjoon.gold.gold4.p1806A;

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
        int S = scan.nextInt();

        int[] arr = new int[N + 1];
        long sum = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            sum += arr[i];
        }

//        System.out.println(Arrays.toString(arr));

        /*

         */

        if(sum >= S) {
            int left = 0;
            int right = 0;
            int min = Integer.MAX_VALUE;
            long total = 0;

            while (left <= N && right <= N) {
                if(total >= S && min > right - left) min = right - left;

                if(total < S) total += arr[right++];
                else total -= arr[left++];
            }

            System.out.println(min);
        } else {
            System.out.println(0);
        }
    }
}
