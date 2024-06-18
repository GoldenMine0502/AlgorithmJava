package kr.goldenmine.baekjoon.silver.silver1.p2785;

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
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        if(N == 1) {
            System.out.println(0);
            return;
        }
        if(N == 2) {
            System.out.println(1);
            return;
        }
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr); // 최대 N - 1개에 대해 고리 전체를 이용하면 연결할 고리의 개수를 하나 줄일 수 있다.

        int index = 0;
        int last = N - 1;
        int count = 0;
        while(true) {
            if(index >= last) break;

            if(arr[index] >= 1) {
                last--;
                arr[index]--;
                count++;
            }
            if(arr[index] == 0) {
                index++;
            }
        }
        System.out.println(count);
//        System.out.println(index + ", " + last + ", " + count);
//        for(int i = 0; i < N; i++) {
//            if(arr[i] < maxChain) {
////                System.out.println(maxChain);
//                maxChain -= arr[i];
//                index++;
//            } else {
//
//            }
//        }
////        System.out.println(Arrays.toString(arr));
////        System.out.println(index + ", " + maxChain);
//        // 1 3 4 2
//        System.out.println(N - index - 2);
    }
}
