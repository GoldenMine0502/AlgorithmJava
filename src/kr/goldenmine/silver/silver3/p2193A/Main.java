package kr.goldenmine.silver.silver3.p2193A;

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

//    public static int solve(String str, int N) {
//        if(str.length() == N) return 1;
//
//        int num = 0;
//
//        if(str.charAt(str.length() - 1) != '1')
//            num += solve(str + "1", N);
//
//        num += solve(str + "0", N);
//
//        return num;
//    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        long cache[] = new long[N+1];
        cache[0] = 0;
        cache[1] = 1;
        for (int i = 2; i <= N; i++){
            cache[i] = cache[i-1] + cache[i-2];
        }

        System.out.println(cache[N]);
    }
}
