package kr.goldenmine.baekjoon.gold.gold4.p1915;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
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

        int Y = scan.nextInt();
        int X = scan.nextInt();

        byte[][] arr = new byte[Y + 1][X + 1];

        for(int y = 1; y <= Y; y++) {
            for(int x = 1; x <= X; x++) {
                arr[y][x] = scan.read();
            }
            scan.read(); // \n read
        }

        int[][] dp = new int[Y + 1][X + 1];

        int max = 0;
        for(int y = 1; y <= Y; y++) {
            for(int x = 1; x <= X; x++) {
                if(arr[y][x] == '1') {
                    dp[y][x] = Math.min(dp[y - 1][x - 1], Math.min(dp[y - 1][x], dp[y][x - 1])) + 1;
                    max = Math.max(max, dp[y][x]);
                }
            }
        }

        System.out.println(max * max);
    }
}
