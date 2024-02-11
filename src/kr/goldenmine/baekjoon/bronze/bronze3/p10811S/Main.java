package kr.goldenmine.baekjoon.bronze.bronze3.p10811S;

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

    public static void swap(int[] arr, int start, int finish) {
        int half = (start + finish) / 2;
        for(int i = start; i <= half; i++) {
            int temp = arr[i];
            arr[i] = arr[finish - i + start];
            arr[finish - i + start] = temp;
//            System.out.println(i + ", " + (finish - i));
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        /*
5 4
1 2
3 4
1 4
2 2

1 2 3 4 5
2 1 3 4 5
2 1 4 3 5
3 4 1 2 5

         */
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        for(int i = 0; i < M; i++) {
            swap(arr, scan.nextInt() - 1, scan.nextInt() - 1);
        }
        StringBuilder sb = new StringBuilder();

        for(int j = 0; j < N; j++) {
            sb.append(arr[j]);
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
