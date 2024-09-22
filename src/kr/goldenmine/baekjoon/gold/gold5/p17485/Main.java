package kr.goldenmine.baekjoon.gold.gold5.p17485;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
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

        // 0 = 왼쪽, 1 = 중간, = 2 오른쪽에서
        // if문의 과도한 사용은 실행속도를 늦춤
        int[][][] dp = new int[Y + 1][(X + 1) + 1][3];

        int MAX_VALUE = 999_9999;

        for(int y = 1; y <= Y; y++) {
            dp[y][0][0] = MAX_VALUE;
            dp[y][0][1] = MAX_VALUE;
            dp[y][0][2] = MAX_VALUE;

            dp[y][X + 1][0] = MAX_VALUE;
            dp[y][X + 1][1] = MAX_VALUE;
            dp[y][X + 1][2] = MAX_VALUE;

            for(int x = 1; x <= X; x++) {
                int current = scan.nextInt();

                dp[y][x][0] = Math.min(dp[y - 1][x - 1][1], dp[y - 1][x - 1][2]) + current;
                dp[y][x][1] = Math.min(dp[y - 1][x    ][0], dp[y - 1][x    ][2]) + current;
                dp[y][x][2] = Math.min(dp[y - 1][x + 1][0], dp[y - 1][x + 1][1]) + current;
            }
        }

        int min = MAX_VALUE;
        for(int x = 1; x <= X; x++) {
            min = Math.min(min, dp[Y][x][0]);
            min = Math.min(min, dp[Y][x][1]);
            min = Math.min(min, dp[Y][x][2]);
        }
        System.out.println(min);
    }
}
