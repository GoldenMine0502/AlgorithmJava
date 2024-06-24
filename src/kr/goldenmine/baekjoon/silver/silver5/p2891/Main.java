package kr.goldenmine.baekjoon.silver.silver5.p2891;

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
        int S = scan.nextInt();
        int R = scan.nextInt();

        // arr = 현재 가지고 있는 카약의 개수
        int[] arr = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            arr[i] = 1;
        }

        // 카약 침몰~~
        for(int i = 1; i <= S; i++) {
            arr[scan.nextInt()]--;
        }

        // 여분의 카약
        for(int i = 1; i <= R; i++) {
            arr[scan.nextInt()]++;
        }

        // 빌려줄 수 있는 팀이 하나인 경우

        boolean lended = true;

        while(lended) {
            lended = false;
            boolean one = false;
            for (int i = 1; i <= N; i++) {

                // 빌려주기가 가능함
                if (arr[i] == 2) {
                    int lendCount = 0;
                    boolean left = false;
                    if (arr[i - 1] == 0) {
                        lendCount++;
                        left = true;
                    }
                    if (i < N && arr[i + 1] == 0) {
                        lendCount++;
                    }
                    if (lendCount == 1) {
                        arr[i]--;
                        if (left) {
                            arr[i - 1]--;
                        } else {
                            arr[i + 1]--;
                        }
                        lended = true;
                        one = true;
                    }
                }
            }

            if(!one) {

                for (int i = 1; i <= N; i++) {

                    // 빌려주기가 가능함
                    if (arr[i] == 2) {
                        int lendCount = 0;
                        boolean left = false;
                        if (arr[i - 1] == 0) {
                            lendCount++;
                            left = true;
                        }
                        if (i < N && arr[i + 1] == 0) {
                            lendCount++;
                        }
                        if (lendCount == 2) {
                            arr[i]--;
                            if (left) {
                                arr[i - 1]--;
                            } else {
                                arr[i + 1]--;
                            }
                            lended = true;
                            one = true;
                        }
                    }
                }
            }
        }
    }
}
