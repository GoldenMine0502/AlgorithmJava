package kr.goldenmine.baekjoon.gold.gold2.p1508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

    static class Point {
        int index;
        int value;

        Point(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }
    }

    static int N;
    static int M;
    static int K;

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();

        int[] arr = new int[K];
        for(int i = 0; i < K; i++) {
            arr[i] = scan.nextInt();
        }
        Point[] diff = new Point[K - 1];
        for(int i = 0; i < K - 1; i++) {
            diff[i] = new Point(i, arr[i + 1] - arr[i]);
        }
        Arrays.sort(diff, (o1, o2) -> {
            int diff1 = Integer.compare(o1.value, o2.value);
            if(diff1 == 0) {
                return Integer.compare(o1.index, o2.index);
            } else {
                return diff1;
            }
        });

        System.out.println(Arrays.toString(diff));
        alwaysSide(diff);
        computeDefault(diff);
    }

    static int[] alwaysSide(Point[] diff) {
        int[] res = new int[K];
        if(M >= 2)
            res[0] = 1;
        res[K - 1] = 1;
        int count = 0;
        for(int i = 0; i < K - 1; i++) {
            if(count == M - 2) break;

            int index = diff[K - 2 - i].index;
            if(index != 0) {
                res[index] = 1;
                count++;
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    static int[] computeDefault(Point[] diff) {
        int[] res = new int[K];
        for(int i = 0; i < M; i++) {
            int index = diff[K - 2 - i].index;
            res[index] = 1;
        }

        System.out.println(Arrays.toString(res));

        return res;
    }
}
