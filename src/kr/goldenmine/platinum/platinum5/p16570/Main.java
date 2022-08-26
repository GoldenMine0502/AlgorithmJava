package kr.goldenmine.platinum.platinum5.p16570;

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

    public static int[] getFailure(int[] key) {
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] arr = new int[N];
        int[] reversed = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            reversed[N - i - 1] = arr[i];
        }

        int[] fail = getFailure(reversed);

//        System.out.println(Arrays.toString(fail));

        int max = 0;

        for(int i = 0; i < fail.length; i++) {
            max = Math.max(max, fail[i]);
        }

        int count = 0;
        for(int i = 0; i < fail.length; i++) {
            if(max == fail[i]) {
                count++;
            }
        }

        if(max != 0) {
            System.out.println(max + " " + count);
        } else {
            System.out.println(-1);
        }
    }
}
