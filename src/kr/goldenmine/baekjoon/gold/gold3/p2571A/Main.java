package kr.goldenmine.baekjoon.gold.gold3.p2571A;

public class Main {
    static class Reader {
        final int SIZE = 1 << 10;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int[][] map = new int[101][101];
        int N = scan.nextInt();

        for(int i = 0; i < N; i++) {
            int X = scan.nextInt();
            int Y = scan.nextInt();

            for(int y = Y; y < Y + 10; y++) {
                for(int x = X; x < X + 10; x++) {
                    map[y][x] = 1;
                }
            }
        }

        // y 누적합
        int[][] ys = new int[101][101];
        for(int y = 1; y <= 100; y++) {
            for(int x = 1; x <= 100; x++) {
                if(map[y][x] == 1) {
                    ys[y][x] = ys[y - 1][x] + 1;
                } else {
                    ys[y][x] = 0;
                }
            }
        }

//        for(int y = 0; y <= 100; y++) {
//            System.out.println(Arrays.toString(ys[y]));
//        }

        int max = 0;

        for(int y = 1; y <= 100; y++) {
            for (int x = 1; x <= 100; x++) {
                int minHeight = 100;

                for(int h = x; h <= 100; h++) {
                    minHeight = Math.min(minHeight, ys[y][h]);
                    max = Math.max(max, minHeight * (h - x + 1));
                }
            }
        }

        System.out.println(max);

        // 해당 y,x를 기준점으로 구할 수 있는 높이의 최댓값
        /*
00000
01010
01000
00010
01010

(1, 1), (2, 1), (3, 1), (4, 1), (5, 1)
(1, 2), (0, 0), (1, 2), (2, 2), (3, 2)
(1, 3), (0, 0), (1, 3), (2, 3), (3, 3)
(1, 4), (2, 1), (3, 1), (0, 0), (1, 4)

0 0 0 0 0
0 1 0 0 0
0 0 0 1 0
0 0 0 1 0
0 1 0 0 0

1 2 3 4 5
1 0 1 0 1
1 2 3 4 5
1 2 3 0 1
1 0 1 0 1

1 1 1 1 1
2 0 2 0 2
3 1 3 1 3
4 2 4 0 4
5 0 5 0 5
         */
    }
}
