package kr.goldenmine.baekjoon.silver.silver1.p16139A;

import java.io.*;
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

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        String str = scan.next();
//        char[] arr = str.toCharArray();

        int[][] cache = new int[26][200001];

        for(int i = 1; i <= str.length(); i++) {
            int index = str.charAt(i - 1) - 'a';
            for(int j = 0; j < 26; j++) {
                cache[j][i] = (i > 0 ? cache[j][i - 1] : 0) + ((j == index) ? 1 : 0);
            }
        }

//        for(int i = 0; i < arr.length; i++) {
//            for(int j = 0; j < 26; j++) {
//                System.out.print(cache[i][j] + " ");
//            }
//            System.out.println();
//        }

        int N = scan.nextInt();

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < N; i++) {
            char ch = scan.next().charAt(0);
            int left = scan.nextInt();
            int right = scan.nextInt();

//            System.out.println(cache[ch - 'a'][right + 1] - cache[ch - 'a'][left]);

            output.write(String.valueOf(cache[ch - 'a'][right + 1] - cache[ch - 'a'][left]));
            output.newLine();
        }

        output.flush();
    }
}
