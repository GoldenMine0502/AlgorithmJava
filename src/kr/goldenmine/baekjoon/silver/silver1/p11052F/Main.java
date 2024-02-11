package kr.goldenmine.baekjoon.silver.silver1.p11052F;


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

    public static void main(String[] args) throws InterruptedException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] arr = new int[N + 1];

        for (int i = 0; i < N; i++) {
            arr[i + 1] = scan.nextInt();
        }

        int[] cache = new int[N + 1];

        cache[0] = 0;
        if (N > 0) cache[1] = arr[1] * N;

        for (int i = 2; i <= N; i++) {
            int divide = N / i;
//            int mod = N % i;

            int max = 0;

            for (int j = i; j > 0; j--) {
//                for(int k = 1; k <= divide; k++) {
                int price = arr[i] * divide;
                int mod = N % i;
//                System.out.println("a " + i + ", " + j + ", " + divide + ", " + mod + ", " + price);

                int inner = j;
                while (mod > 0) {
                    int mod2 = mod / inner;
                    if (inner * mod2 - mod >= 0) {
                        price += arr[inner] * mod2;
                        mod -= inner * mod2;
                    } else {
                        inner--;
                        if(inner == 0) break;
                    }

//                    Thread.sleep(500L);
//                    System.out.println(mod + ", " + mod2 + ", " + inner);
                }

//                System.out.println(i + ", " + j + ", " + divide + ", " + mod + ", " + price);

                max = Math.max(max, price);
            }
//            }

            cache[i] = max;
        }

//        System.out.println(Arrays.toString(cache));

        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, cache[i]);
        }

        System.out.println(max);
    }
}
