package kr.goldenmine.gold.gold3.p5015;

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


    /*
*a*a*a
1
abababa
*.*
1
readme
     */

    public static int[][] dp;

    public static int check(String p, String s, int pLen, int sLen) {
//        System.out.println(s + ": " + pLen + ", " + sLen);

        if(p.length() == pLen && s.length() == sLen) return 1;
        if(p.length() <= pLen) return 0;
        if(s.length() <= sLen && p.charAt(pLen) != '*') return 0;

        if(dp[pLen][sLen] != -1) {
            return dp[pLen][sLen];
        }

        if(p.charAt(pLen) == '*') {
            int match = 0;
//            System.out.println("1" + ": " + pLen + ", " + sLen);
            if(sLen <= s.length()) {
                match |= check(p, s, pLen + 1, sLen + 1);
                match |= check(p, s, pLen + 1, sLen);
                match |= check(p, s, pLen, sLen + 1);
            }
            dp[pLen][sLen] = match;

            return match;
        } else {
//            System.out.println("3" + ": " + pLen + ", " + sLen + ", " + (p.charAt(pLen) == s.charAt(sLen)));
            if(p.charAt(pLen) == s.charAt(sLen)) {
                return dp[pLen][sLen] = check(p, s, pLen + 1, sLen + 1);
//                dp[pLen][sLen] = match;
            } else {
                return dp[pLen][sLen] = 0;
            }
        }
//        dp[pLen][sLen] = match;
//        return match;
    }

    public static void main(String[] args) {
        final FastReader scan = new FastReader();

        final String P = scan.next(); // 1 <= P <= 100
        final int N = scan.nextInt(); // 1 <= N <= 100

        final List<String> lines = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            lines.add(scan.next());
        }

        dp = new int[102][102];

        for(String line : lines) {
            for(int i = 0; i < 102; i++) {
                for(int j = 0; j < 102; j++) {
                    dp[i][j] = -1;
                }
            }

            if(check(P, line, 0, 0) == 1) {
                System.out.println(line);
            }
        }
    }
}
