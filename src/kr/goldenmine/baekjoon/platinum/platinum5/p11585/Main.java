package kr.goldenmine.baekjoon.platinum.platinum5.p11585;

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

    public static int[] getFailure(char[] key) {
        int[] pi = new int[key.length];
        pi[0] = 0;

        int j = 0;

        for (int i = 1; i < key.length; i++) {
            while (j > 0 && key[i] != key[j])
                j = pi[j - 1];

            if (key[i] == key[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static List<Integer> kmp(char[] text, char[] key) {
        List<Integer> indices = new ArrayList<>();

        int[] pi = getFailure(key);

        int j = 0;

        for (int i = 0; i < text.length; i++) {
            while (j > 0 && text[i] != key[j])
                j = pi[j - 1];

            if (text[i] == key[j]) {
                if (j == key.length - 1) {
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

        int N = scan.nextInt();

        char[] arr = new char[N * 2];
        char[] arr2 = new char[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.next().charAt(0);
            arr[i + N] = arr[i];
        }

        for(int i = 0; i < N; i++) {
            arr2[i] = scan.next().charAt(0);
        }

        int[] pi = getFailure(arr2);

//            System.out.println(Arrays.toString(pi));

        int multiplier;
        int len = (arr2.length - pi[arr2.length - 1]);
        if(arr2.length % len == 0) {
            multiplier = arr2.length / len;
        } else {
            multiplier = 1;
        }

//        System.out.println(multiplier);

        int gcd = gcd(multiplier, arr2.length);

        System.out.println(multiplier / gcd + "/" + arr2.length / gcd);
    }

    public static int gcd(int a, int b) {
        if(a%b == 0) {
            return b;
        }
        return gcd(b, a%b);
    }
}
