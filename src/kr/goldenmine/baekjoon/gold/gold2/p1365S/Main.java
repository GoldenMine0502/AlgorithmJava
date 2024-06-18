package kr.goldenmine.baekjoon.gold.gold2.p1365S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

    public static int lowerBound(List<Integer> arr, int key) {
        int lo = 0;
        int hi = arr.size();

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            if (key <= arr.get(mid)) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[] list = new int[N];
        for(int i = 0; i < N; i++) {
            list[i] = scan.nextInt();
        }

        List<Integer> LIS = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int v = list[i];
            int res = lowerBound(LIS, v);
            if(res == LIS.size()) {
                LIS.add(v);
            } else {
                LIS.set(res, v);
            }
        }
        System.out.println(list.length - LIS.size());
    }
}
