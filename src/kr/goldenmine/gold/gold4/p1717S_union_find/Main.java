package kr.goldenmine.gold.gold4.p1717S_union_find;

import java.io.*;
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

    public static int find(int[] arr, int value) {
        if(arr[value] == value)
            return value;
        return arr[value] = find(arr, arr[value]);
    }

    public static void merge(int[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);
        if(x == y) return;
        arr[y] = x;
    }

    public static boolean isUnion(int[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);

        return x == y;
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        for(int i = 0; i < M; i++) {
            int A = scan.nextInt();
            int B = scan.nextInt();
            int C = scan.nextInt();

            if(A == 0) { // merge
                merge(arr, B, C);
            } else { // isUnion
                writer.write(isUnion(arr, B, C) ? "YES" : "NO");
                writer.newLine();
            }
        }

        writer.close();
    }
}
