package kr.goldenmine.platinum.platinum5.p8111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
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

    public static String getValue(int N) {
        BigInteger bN = new BigInteger(String.valueOf(N));
        Queue<String> texts = new LinkedList<>();
        texts.add("1"); // 1이 적어도 하나 이상인데 0으로 시작하면 안된다... 그러면 1부터 시작해야 하는거 아닌가

        while(!texts.isEmpty()) {
            String text = texts.poll();


        }

        return null;
    }

    public static void main(String[] args) {
        int N = 17;

        for(int i = 0; i < 100000000; i++) {
            String text = String.valueOf(N * i);

            if(!text.contains("2") && !text.contains("3") && !text.contains("4") && !text.contains("5") &&
                    !text.contains("6") && !text.contains("7") && !text.contains("8") && !text.contains("9")) {
                System.out.println(text + ", " + i);
            }
        }
    }
//    public static void main(String[] args) {
//        FastReader scan = new FastReader();
//
//        int T = scan.nextInt();
//        for(int i = 0; i < T; i++) {
//            int N = scan.nextInt();
//
//
//        }
//    }
}
