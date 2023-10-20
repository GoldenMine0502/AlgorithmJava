package kr.goldenmine.silver.silver2.p1024S;

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

        long N = scan.nextInt();
        int L = scan.nextInt();

        boolean get = false;
        for(int k = L - 1; k < 100; k++) {
            // ((2N/(k+1)) - k) = i
            long value = ((2 * N)/(k + 1) - k) / 2;

            long sum = 0;
            for(int i = 0; i <= k; i++) {
                sum += value + i;
            }
//            System.out.println(k + ": " + value + ", " + sum);
            if(sum == N && value >= 0) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i <= k; i++) {
                    sb.append(value + i);
                    sb.append(" ");
                }
                System.out.println(sb);
                get = true;
                break;
            }
        }
        if(!get) {
            System.out.println(-1);
        }
        // 1부터 n까지 합 = n(n+1)/2
        // n부터 l까지 합 = 개수*(n+l)/2

        // i 부터 i + k까지 합 = (k+1)(i + i + k)/2
        // (k+1)(2i + k)/2

        // (k+1)(2i + k)/2 = N
        // (2N/(k+1)) - k = 2i
        // ((2N/(k+1)) - k) = i
    }
}
