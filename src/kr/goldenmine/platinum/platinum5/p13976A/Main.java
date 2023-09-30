package kr.goldenmine.platinum.platinum5.p13976A;

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

    static final long MOD = 1_000_000_007L;
    public static void main(String[] args) {
        // https://memoacmicpc.tistory.com/entry/%EB%B0%B1%EC%A4%80-13976%EB%B2%88-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0-2
        long[][] matrix = {
                { 4, -1},
                { 1,  0}
        };

        FastReader scan = new FastReader();

        long N = scan.nextLong();

//        long[][] m = {
//                {1, 1}
//        };
//
//        long[][] m2 = {
//                {1},
//                {4}
//        };
//
//        long[][] m3 = mul(m, m2);
//        for(int i = 0; i < m3.length; i++) {
//            for (int j = 0; j < m3[i].length; j++) {
//                System.out.print(m3[i][j] + " ");
//            }
//            System.out.println();
//        }
        if(N % 2 == 1) {
            System.out.println(0);
            return;
        }

        long[][] first = new long[][] {
                {3},
                {1}
        };
        long[][] multiplied = pow(matrix, matrix, N / 2);
        long[][] res = mul(multiplied, first);

//        for(int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res[i].length; j++) {
//                System.out.print(res[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(res[1][0]);
    }

    // arr = a * b
    // arr.length = a
    // arr2 = b * c
    public static long[][] mul(long[][] arr, long[][] arr2) {
        long[][] result = new long[arr.length][arr2[0].length];

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr2[0].length; j++) {
                long sum = 0;

                for(int k = 0; k < arr2.length; k++) {
//                    System.out.println(arr[i][k] + ", " + arr[k][j] + "=" + arr[i][k] * arr[k][j] + ", " + sum);
                    sum += ((arr[i][k] % MOD) * (arr2[k][j] % MOD)) % MOD;
                    sum = (sum + MOD) % MOD;
//                    sum %= MOD;
                }

                result[i][j] = sum;
            }
        }

        return result;
    }

    public static long[][] pow(long[][] original, long[][] arr, long exponent) {
        long[][] result = new long[arr.length][arr.length];

        if(exponent == 1) {
            // just copy
            for(int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    result[i][j] = arr[i][j];
                }
            }

            return result;
        }
        if(exponent == 2) {
            return mul(arr, arr);
        }

//        System.out.println(exponent);

        long[][] half = pow(original, arr, exponent / 2);

        if(exponent % 2 == 0) {
            return mul(half, half);
        } else {
            return mul(original, mul(half, half));
        }
    }
}
