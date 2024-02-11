package kr.goldenmine.baekjoon.silver.silver5.p10384S;

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

    public static int pangram(String text) {
//        int[] cache = new int[127];
        int[] cache = new int['z' - 'a' + 1];

        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if('A' <= ch && ch <= 'Z') {
                cache[ch - 'A']++;
            }

            if('a' <= ch && ch <= 'z') {
                cache[ch - 'a']++;
            }
//            cache[ch]++;
        }

        int min = cache[0];
        for(int i = 1; i < 'z' - 'a' + 1; i++) {
            min = Math.min(min, cache[i]);
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
//        for(int i = 0; i <= 127; i++) {
//            System.out.println(i + ", " + (char)i);
//        }

//        FastReader scan = new FastReader();
//        Scanner scan = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());

//        scan.nextLine();

        for(int i = 0; i < T; i++) {
            String text = reader.readLine();
            int result = pangram(text);
//            System.out.println(pangram(text));

            System.out.print("Case " + (i + 1) + ": ");
            switch(result) {
                case 0:
                    System.out.println("Not a pangram");
                    break;
                case 1:
                    System.out.println("Pangram!");
                    break;
                case 2:
                    System.out.println("Double pangram!!");
                    break;
                case 3:
                    System.out.println("Triple pangram!!!");
                    break;
            }
        }

    }
}
