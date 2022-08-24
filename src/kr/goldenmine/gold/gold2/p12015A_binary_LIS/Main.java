package kr.goldenmine.gold.gold2.p12015A_binary_LIS;

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

    public static int binarySearch(int[] arr, int size, int value) {
        int left = 0;
        int right = size;

        while(left < right) {
            int mid = (left + right) / 2;

            int arrValue = arr[mid];

            if(arrValue >= value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    //https://www.acmicpc.net/problem/12015
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        int[] LIS = new int[N]; // 일단 마지막 인덱스가 가장 큰값이긴 하겠지.
        int size = 0;

        for(int i = 0; i < N; i++) {
            int value = arr[i];
            int last = size > 0 ? LIS[size - 1] : Integer.MIN_VALUE;

            if(value > last) { // 추가
                LIS[size++] = value;
            } else { // 대치
                int index = binarySearch(LIS, size, value);
                LIS[index] = value;
            }
//            else { // 그냥 아무것도 하지말고 스킵하면 될듯?
//
//            }
        }

//        System.out.println(Arrays.toString(LIS));
        System.out.println(size);
//        for(int i = 0; i < size; i++) {
//            System.out.print(LIS[i] + " ");
//        }
    }
}
