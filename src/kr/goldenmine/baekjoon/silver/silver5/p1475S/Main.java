package kr.goldenmine.baekjoon.silver.silver5.p1475S;

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

        int[] digits = new int[10];

        while(N > 0) {
            digits[N % 10]++;
            N /= 10;
        }

        // 모든 9를 6처럼 생각함
        digits[6] += digits[9];

        int count = 0;
        while(true) {
            int zero = 0;
            for(int i = 0; i < 9; i++) {
                if(digits[i] <= 0) {
                    zero++;
                }
            }
            if(zero == 9) break;

            // 6은 두개 뺀다
            for(int i = 0; i < 9; i++) {
                digits[i]--;
            }
            digits[6]--;

            count++;
        }

        System.out.println(count);
    }
}
