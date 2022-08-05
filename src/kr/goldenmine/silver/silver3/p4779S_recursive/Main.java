package kr.goldenmine.silver.silver3.p4779S_recursive;

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

        boolean hasMoreReadableContents() {
            try {
                return (st != null && st.hasMoreTokens()) || (br.ready());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    public static void remove(char[] arr, int left, int right) {
        if(right - left <= 1) return;

        int divide3 = (right - left) / 3;

        for(int i = left + divide3; i < left + 2 * divide3; i++) {
            arr[i] = ' ';
        }

        remove(arr, left, left + divide3);
        remove(arr, left + 2 * divide3, left + 3 * divide3);
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        do {
            int N = scan.nextInt();
            int N3 = (int) Math.pow(3, N);

            char[] arr = new char[N3];
            for (int i = 0; i < N3; i++) {
                arr[i] = '-';
            }

            remove(arr, 0, N3);

            StringBuilder sb = new StringBuilder(N3);
            for (int i = 0; i < N3; i++) {
                sb.append(arr[i]);
            }
            System.out.println(sb);
        } while(scan.hasMoreReadableContents());
    }
}
