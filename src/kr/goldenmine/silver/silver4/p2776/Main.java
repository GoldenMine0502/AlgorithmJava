package kr.goldenmine.silver.silver4.p2776;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    // 내림차순 기준
    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while(true) {
            int mid = (left + right) / 2;
            if(arr[mid] == value) {
                return mid;
            } else if(arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if(left > right) return -1;
        }
    }

    static FastReader scan = new FastReader();

    public static void solve() {
        int N = scan.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        int M = scan.nextInt();

        StringBuilder sb = new StringBuilder(N * 2);

        for(int i = 0; i < M; i++) {
            int value = scan.nextInt();

            sb.append(binarySearch(arr, value) >= 0 ? 1 : 0);

            if(i != M - 1)
                sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) {
        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            solve();
        }
    }
}
