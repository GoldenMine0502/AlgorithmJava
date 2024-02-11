package kr.goldenmine.baekjoon.platinum.platinum4.p10266A;

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

    public static int[] getPi(int[] key) {
        int[] pi = new int[key.length];
        pi[0] = 0;

        int j = 0;

        for(int i = 1; i < key.length; i++) {
            while(j > 0 && key[i] != key[j])
                j = pi[j - 1];

            if(key[i] == key[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static List<Integer> kmp(int[] text, int[] key) {
        List<Integer> indices = new ArrayList<>();

        int[] pi = getPi(key);

        int j = 0;

        for(int i = 0; i < text.length; i++) {
            while(j > 0 && text[i] != key[j])
                j = pi[j - 1];

            if(text[i] == key[j]) {
                if(j == key.length - 1) {
                    indices.add(i - key.length + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        return indices;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        final int SIZE = 360000;
        int N = scan.nextInt();

        int[] arr = new int[SIZE * 2];
        int[] arr2 = new int[SIZE];

//        Set<Integer> diffs = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int input = scan.nextInt();
            arr[input] = 1;
            arr[input + SIZE] = 1;
        }

        for(int i = 0; i < N; i++) {
            int input = scan.nextInt();
            arr2[input] = 1;
        }
        // 0에서 넘어가는 부분 처리를 잘 해야함...

        List<Integer> indices = kmp(arr, arr2);

        System.out.println(indices.size() > 0 ? "possible" : "impossible");
//        Arrays.sort(arr);
//        Arrays.sort(arr2);
//
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr2));
    }
}
