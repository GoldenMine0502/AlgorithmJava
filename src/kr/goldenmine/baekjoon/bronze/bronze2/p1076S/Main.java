package kr.goldenmine.baekjoon.bronze.bronze2.p1076S;

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

    public static int getCount(String text) {
        switch(text) {
            case "black": return 0;
            case "brown": return 1;
            case "red": return 2;
            case "orange": return 3;
            case "yellow": return 4;
            case "green": return 5;
            case "blue": return 6;
            case "violet": return 7;
            case "grey": return 8;
            case "white": return 9;
            default: return -1;
        }
    }

    public static int getMultiplier(String text) {
        switch(text) {
            case "black":  return             1;
            case "brown":  return            10;
            case "red":    return           100;
            case "orange": return          1000;
            case "yellow": return         10000;
            case "green":  return       100_000;
            case "blue":   return     1_000_000;
            case "violet": return    10_000_000;
            case "grey":   return   100_000_000;
            case "white":  return 1_000_000_000;
            default: return -1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        for(int i = 0; i < 110_000_000; i++) {
//            for(int j = 0; j < 20; j++) {
//                if(i < j) {
//                    long k = 1;
//                    k = k + 20;
//                    // ignore
//                }
//            }
//        }
        FastReader scan = new FastReader();

        String text1 = scan.next();
        String text2 = scan.next();
        String text3 = scan.next();

        System.out.println((long) (getCount(text1) * 10 + getCount(text2)) * getMultiplier(text3));
    }
}
