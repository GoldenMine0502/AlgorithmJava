package kr.goldenmine.baekjoon.silver.silver1.p1495A;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int S = scan.nextInt();
        int M = scan.nextInt();

        int[] arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = scan.nextInt();
        }

        int[] volumes = new int[M + 1]; // 해당 볼륨을 지난 마지막 인덱스
        for(int i = 0; i <= M; i++) {
            volumes[i] = -1;
        }

        volumes[S] = 0;


        List<Integer> buffer = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            buffer.clear();
            for(int j = 0; j <= M; j++) {
                if(volumes[j] == i - 1) { // 이전 값에서 가능한 후보 목록 등록
                    int minus = j - arr[i];
                    int plus = j + arr[i];

//                    System.out.println(j + ", " + minus + ", " + plus);
                    if(minus >= 0) {
                        buffer.add(minus);
                    }
                    if(plus <= M) {
                        buffer.add(plus);
                    }
                }
            }

//            System.out.println(buffer);

            for(int k : buffer) volumes[k] = i; // 해당 인덱스로 지정
        }

        int max = -1;
        for(int i = 0; i <= M; i++) {
            if(volumes[i] == N) max = Math.max(max, i);
        }

        System.out.println(max);
        // 14 40 243
        // 74 39 127 95 63 140 99 96 154 18 137 162 14 88
        // 114, (153, 75), (26, 202), (121, 107), (184, 58, 170, 44)
    }
}