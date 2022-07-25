package kr.goldenmine.silver.silver3.p2579F;

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
//
//    public static int solve(int[] arr, int[] cache, int index, int jumps) {
//        if(index == -1) return 0;
//        if(index == 0) return cache[0][0] = arr[0];
////        if(index == 1) return cache[1] = Math.max(arr[0], arr[0] + arr[1]);
//
////        if(jumps >= 3) return 0;
//        if(cache[index][0] != 0) return cache[index];
//
//        if(jumps < 2) {
//            cache[index]
//            return cache[index] = arr[index] + Math.max(solve(arr, cache, index - 2, 0), solve(arr, cache, index - 1, jumps + 1));
//        } else {
//            return cache[index] = arr[index] + solve(arr, cache, index - 2, 0);
//        }
//    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        int[] cache = new int[N];
        cache[0] = arr[0];

//        for(int i = 1; i < N; i++) {
//            cache[i] =
//        }

//        int max = solve(arr, cache, N - 1, 0);

//        System.out.println(max);

//        for(int i = 0; i < N; i++) {
//            System.out.println(cache[i]);
//        }
    }
}
