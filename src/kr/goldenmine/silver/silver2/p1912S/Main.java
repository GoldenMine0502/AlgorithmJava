package kr.goldenmine.silver.silver2.p1912S;

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
        int[] cache = new int[N];

//        for(int i = 0; i < N; i++) {
//            cache[i] = Integer.MIN_VALUE;
//        }

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        cache[0] = arr[0];

        for (int i = 1; i < N; i++) {
            cache[i] = Math.max(cache[i - 1] + arr[i], arr[i]);
        }

//        System.out.println(Arrays.toString(cache));

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            max = Math.max(cache[i], max);
        }

        System.out.println(max);
    }
}
