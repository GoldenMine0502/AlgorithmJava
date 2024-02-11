package kr.goldenmine.baekjoon.silver.silver1.p1074S;

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

//    public static int recursive(int N, int x, int y, int depth) {
//        int size = (int) Math.pow(2, depth) * (int) Math.pow(2, depth);
//
//        if(size == 4) {
//            if(x == 0 && y == 0) return 0;
//            else if(x == 1 && y == 0) return 1;
//            else if(x == 0 && y == 1) return 2;
//            else return 3;
//        }
//
//        if(x < size / 2 && y < size / 2) { // 왼쪽 위
//            return recursive(N, x, y, )
//        }
//    }
    public static int recursive(int x, int y, int rangeStartX, int rangeStartY, int rangeFinishX, int rangeFinishY) {
        final int size = rangeFinishX - rangeStartX;
        final int sizeSquare = size * size / 4;

        if(size == 2) { // 마지막
            int localX = x - rangeStartX;
            int localY = y - rangeStartY;

            if(localX == 0 && localY == 0) {
                return 0;
            } else if(localX == 1 && localY == 0) {
                return 1;
            } else if(localX == 0 && localY == 1) {
                return 2;
            } else {
                return 3;
            }
        }

        int middleX = (rangeFinishX - rangeStartX) / 2 + rangeStartX; // 4 * 4 기준 (3 - 0) / 2 + 1 = 2
        int middleY = (rangeFinishY - rangeStartY) / 2 + rangeStartY;

        // top left
        if(x < middleX && y < middleY) {
            return recursive(x, y, rangeStartX, rangeStartY, middleX, middleY) + sizeSquare * 0;
        }
        // top right
        else if(x >= middleX && y < middleY) {
            return recursive(x, y, middleX, rangeStartY, rangeFinishX, middleY) + sizeSquare * 1;
        }
        // bottom left
        else if(x < middleX/* && y >= middleY */) {
            return recursive(x, y, rangeStartX, middleY, middleX, rangeFinishY) + sizeSquare * 2;
        }
        // bottom right
        else {
            return recursive(x, y, middleX, middleY, rangeFinishX, rangeFinishY) + sizeSquare * 3;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int y = scan.nextInt();
        int x = scan.nextInt();

        int size = (int) Math.pow(2, N);

        int result = recursive(x, y, 0, 0, size, size);

        System.out.println(result);
    }
}
