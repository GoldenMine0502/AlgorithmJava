package kr.goldenmine.baekjoon.platinum.platinum1.p17165;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        int[][] opposites = new int[N][N];

        for (int j = 0; j < N; j++) {
            String text = scan.next();
            for (int i = 0; i < N; i++) {
                char ch = text.charAt(i);
                opposites[j][i] = ch;
            }
        }

        int maxIndex = -1;
        int maxWinCount = 0;
        for (int i = 0; i < N; i++) {
            List<Integer> nexts = new ArrayList<>();
            int winCount = 0;
            for (int f = 0; f < N; f++) {
                if (opposites[i][f] == 'W') {
                    nexts.add(f);
                    winCount++;
                }
            }
            if(winCount > maxWinCount) {
                maxWinCount = winCount;
                maxIndex = i;
            }
        }

        if (maxWinCount < N - 1) {
            System.out.println("2 " + (maxIndex + 1));
        } else {
            System.out.println("1 " + (maxIndex + 1));
        }
    }
}
