package kr.goldenmine.silver.silver5.p18111S;

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

        int Y = scan.nextInt();
        int X = scan.nextInt();
        int inventory = scan.nextInt();

        int[][] arr = new int[Y][X];
        for(int y = 0; y < Y; y++) {
            for(int x = 0; x < X; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        int min = 257;
        int max = -1;

        for(int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if(min > arr[y][x]) {
                    min = arr[y][x];
                }

                if(max < arr[y][x]) {
                    max = arr[y][x];
                }
            }
        }

        /*
3 3 64
64 64 64
60 60 60
62 62 62

3 4 99
0 0 0 0
0 5 5 0
0 0 0 1
         */

        int minTime = Integer.MAX_VALUE;
        int minHeight = -1;

        for(int height = min; height <= max; height++) {
            int toPlace = 0;
            int toBreak = 0;

            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    int value = arr[y][x];
                    toPlace += Math.max(0, height - value);
                    toBreak += Math.max(0, value - height);
                }
            }

            int currentTime = toPlace + toBreak * 2;

            if(toPlace - toBreak <= inventory) {
                if (currentTime <= minTime) {
                    minTime = currentTime;
                    minHeight = height;
                }
            }
        }

        System.out.println(minTime + " " + minHeight);
//        System.out.println(min + ", " + max + ", " + toPlace + ", " + toBreak);
    }
}
