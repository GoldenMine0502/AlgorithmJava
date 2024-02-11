package kr.goldenmine.baekjoon.gold.gold4.p2448F;

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

    public static void recursive(char[][] arr, int startX, int startY, int finishX, int finishY) {
        if(finishX - startX <= 5) {
            // draw star
//            int convertedStartX = startX * 2; // 20, 24
//            int convertedFinishX = finishX * 2;
            int convertedStartX = startX;

            arr[startY + 0][convertedStartX + 2] = '*';
            arr[startY + 1][convertedStartX + 1] = '*';
            arr[startY + 1][convertedStartX + 3] = '*';
            arr[startY + 2][convertedStartX + 0] = '*';
            arr[startY + 2][convertedStartX + 1] = '*';
            arr[startY + 2][convertedStartX + 2] = '*';
            arr[startY + 2][convertedStartX + 3] = '*';
            arr[startY + 2][convertedStartX + 4] = '*';

            return;
            /*
              *
             * *
            *****
             */
        }

        /*
        0 24 -> 6
        6 18 -> 3
        9 12 -> 1

         */
//        { // top
//            int quarter = (finishX - startX) / 4;
//            int topStartX = startX + quarter;
//            int topStartY = startY;
//            int topFinishX = finishX - quarter;
//            int topFinishY = (startY + finishY) / 2;
//
//            System.out.println("top: (" + topStartX + ", " + topStartY + "), (" + topFinishX + ", " + topFinishY + ")");
//
//            recursive(arr, topStartX, topStartY, topFinishX, topFinishY);
//        }

        { // left
            int leftStartX = startX;
            int leftStartY = (startY + finishY) / 2;
            int leftFinishX = (startX + finishX) / 2;
            int leftFinishY = finishY; // 그대로

            System.out.println("left: (" + leftStartX + ", " + leftStartY + "), (" + leftFinishX + ", " + leftFinishY + ")");

            recursive(arr, leftStartX, leftStartY, leftFinishX, leftFinishY);
        }

        { // right
            int rightStartX = (startX + finishX) / 2;
            int rightStartY = (startY + finishY) / 2;
            int rightFinishX = finishX;
            int rightFinishY = finishY;

            System.out.println("right: (" + rightStartX + ", " + rightStartY + "), (" + rightFinishX + ", " + rightFinishY + ")");

            recursive(arr, rightStartX, rightStartY, rightFinishX, rightFinishY);
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt(); // N 최대 1024 * 3

        char[][] arr = new char[N + 3][2 * N + 3]; // 최대 천만개정도

        recursive(arr, 0, 0,  2 * N, N);

        StringBuilder sb = new StringBuilder();
        for(int y = 0; y <= N; y++) {
            for(int x = 0; x <= 2 * N; x++) {
                if(arr[y][x] == '*')
                    sb.append('*');
                else
                    sb.append('.');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
