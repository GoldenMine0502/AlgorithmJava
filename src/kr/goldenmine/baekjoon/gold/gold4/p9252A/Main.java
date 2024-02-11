package kr.goldenmine.baekjoon.gold.gold4.p9252A;

import java.io.*;
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
            newTokenizer();

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
            newTokenizer();

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

        void newTokenizer() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Node {
        int len;

        int lastIndex;


        public Node(int len, int lastIndex) {
            this.len = len;
            this.lastIndex = lastIndex;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "len=" + len +
                    ", lastIndex=" + lastIndex +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        String a = scan.next();
        String b = scan.next();

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for(int i = 1; i <= a.length(); i++) {
            for(int j = 1; j <= b.length(); j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        List<Character> last = new ArrayList<>();

        int x = a.length();
        int y = b.length();

        while(x > 0 && y > 0) {
            if(dp[x][y] == dp[x-1][y]) {
                x--;
            }else if(dp[x][y] == dp[x][y-1]) {
                y--;
            }else {
                last.add(a.charAt(x - 1));
                x--;
                y--;
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(String.valueOf(last.size()));
        writer.newLine();

        for(int i = last.size() -1; i >= 0; i--) {
            writer.write(last.get(i));
        }

        writer.close();
//        for(int i = 1; i <= a.length(); i++) {
//            for (int j = 1; j <= b.length(); j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
