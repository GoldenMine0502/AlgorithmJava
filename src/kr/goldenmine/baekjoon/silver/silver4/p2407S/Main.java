package kr.goldenmine.baekjoon.silver.silver4.p2407S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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

    // 리턴: 가능한 결과의 갯수
//    public static int solve(int N, int M, int count, int index) {
//        if(count >= N) return 1;
//        if(cache[count + 1][index + 1] > 0) return cache[count][index];
//
////        int lastSelect = -1;
//
//        int sum = 0;
//
//        for(int i = index + 1; i < M - N + count + 1; i++) {
////            System.out.println(i + ", " + count);
//            sum += solve(N, M, count + 1, i);
//        }
//
//        cache[count + 1][index + 1] = sum;
//
//        return sum;
//    }

    public static BigInteger solve(BigInteger[][] cache, int N, int M, int count, int index) {
        if (count == 0) return BigInteger.ONE;

        BigInteger sum = BigInteger.ZERO;

        for (int i = index; i < M - count + 1; i++) {
            if (cache[count - 1][i + 1] == null) {
                BigInteger result = solve(cache, N, M, count - 1, i + 1);
                cache[count - 1][i + 1] = result;
                sum = sum.add(result);
            } else {
                sum = sum.add(cache[count - 1][i + 1]);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        // dp 풀이
        FastReader scan = new FastReader();

        BigInteger[][] cache = new BigInteger[110][110];
        int M = scan.nextInt();
        int N = scan.nextInt();

        BigInteger result = solve(cache, N, M, N, 0);
        System.out.println(result);
    }
}
