package kr.goldenmine.baekjoon.silver.silver2.p2885;

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

        int two = 1048576;

        // log_2(x) = log(x) / log(2)
//        System.out.println();
        int first = (int) Math.ceil(Math.log(N) / Math.log(2));
        int lastMinus = first;
        for(int i = first; i >= 0; i--) {
            int power = (int) Math.pow(2, i);
//            System.out.println(power);
            if(N - power >= 0) {
                N -= power;
                lastMinus = i;
            }
        }
        System.out.println((int) Math.pow(2, first) + " " + (first - lastMinus));
//        int count = 0;
//        while(two >= 1) {
//            if(N > two) {
//                count++;
//                N -= two;
//
//                if(first == -1) {
//                    first = two * 2;
//                }
//            } else if(N == two) {
//                if(first == -1) {
//                    first = two;
//                }
//                break;
//            }
//
//            two /= 2;
//        }
//
//        System.out.println(first + " " + count);
    }
}
