package kr.goldenmine.silver.silver1.p5525S;

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

    /*
    1234 -> 4321
    6789 -> 9876
   ~~~23
    4 + 9 -> 13
    3(1)

    3 + 8 -> 11 + 1 -> 12

    32(1)

    ~~~23
     */
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        String text = scan.next();

        int combo = 0;

        int times = 0;

        for(int i = 1; i < text.length() - 1; i++) {
//            System.out.println("index: " + i);
            char ch0 = text.charAt(i - 1);
            char ch1 = text.charAt(i);
            char ch2 = text.charAt(i + 1);

            if(ch0 == 'I' && ch1 == 'O' && ch2 == 'I') {
                combo++;
//                System.out.println(combo + ", " + i);
                i++;
//                threes.add(i - 1);
                // N이 2면 combo가 2일때 1개
                // N이 2면 combo가 3일때 2개
                // N이 2면 combo가 4일때 3개
                // N이 3이면 combo가 3일때 1개
                // N이 4이면 combo가 4일때 2개
            } else {
//                System.out.println("combo: " + combo);
                if(combo >= N) {
                    times += combo - N + 1;
                }
//                if(combo > 0) {
//                    i--;
//                }
                combo = 0;
            }
        }

        if(combo >= N) {
            times += combo - N + 1;
        }

        System.out.println(times);
    }
}
