package kr.goldenmine.baekjoon.silver.silver4.p2670S;

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

        double[] arr = new double[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextDouble();
        }

        double[] max = new double[N];
//        double[] maxReverse = new double[N];
        max[0] = arr[0];
//        max[N - 1] = arr[N - 1];
        double totalMax = 0;
        for(int i = 1; i < N; i++) {
//            int rev = N - i - 1;
            // max[1] = max(max[1], max[0] * max[1]
            // max[2] = max(max[2], max[2] * max[1], max[2] * max[1] * max[0])
            // max[3] = max(max[3], max[3] * max[2])
//            for(int j = 1; j < N; j++) {
//                max[i] = Math.max(max[i], max[])
//            }
            max[i] = Math.max(arr[i], max[i - 1] * arr[i]);
//            maxReverse[rev] = Math.max(arr[rev], max[rev + 1] * arr[rev]);
//            totalMax = Math.max(totalMax, max[i]);
//            totalMax = Math.max(totalMax, maxReverse[rev]);
//            double innerMax = 0D;
//            double multiply = 1D;
//            for(int j = i; j >= 0; j--) {
//                multiply *= arr[j];
//                if(innerMax < multiply) {
//                    innerMax = multiply;
//                } else {
////                    break;
//                }
////                System.out.println(i + ", " + j + ", " + multiply + ", " + arr[i]);
//            }
//            max[i] = innerMax;
            totalMax = Math.max(totalMax, max[i]);
        }

//        for(int i = 0; i < N; i++) {
//            System.out.println(max[i]);
//        }

//        System.out.println(Math.round(totalMax * 1000) / 1000D);
        System.out.printf("%.3f", totalMax);
    }
}
