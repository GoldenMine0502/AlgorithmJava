package kr.goldenmine.platinum.platinum4.p1305S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static int[] getPi(String key) {
        int[] pi = new int[key.length()];
        pi[0] = 0;

        int j = 0;

        for(int i = 1; i < key.length(); i++) {
            while(j > 0 && key.charAt(i) != key.charAt(j))
                j = pi[j - 1];

            if(key.charAt(i) == key.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static List<Integer> kmp(String text, String key) {
        List<Integer> indices = new ArrayList<>();

        int[] pi = getPi(key);

        System.out.println(Arrays.toString(pi));

        int j = 0;

        for(int i = 0; i < text.length(); i++) {
            while(j > 0 && text.charAt(i) != key.charAt(j))
                j = pi[j - 1];

            if(text.charAt(i) == key.charAt(j)) {
                if(j == key.length() - 1) {
                    indices.add(i - key.length() + 1);
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
        String text = scan.next();

//        List<Integer> results = kmp(text, text);

        int[] pi = getPi(text);

        System.out.println(N - pi[N - 1]);
//        for(int i = 0; i < text.length(); i++) {
//
//        }
    }
}
