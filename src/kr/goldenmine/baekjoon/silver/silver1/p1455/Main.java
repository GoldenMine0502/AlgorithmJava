package kr.goldenmine.baekjoon.silver.silver1.p1455;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

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

    static void flip(int[][] map, int x1, int y1, int x2, int y2) {
        for(int x = x1; x <= x2; x++) {
            for(int y = y1; y <= y2; y++) {
                map[y][x] = (map[y][x] + 1) % 2;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int[][] arr = new int[Y][X];
        for(int y = 0; y < Y; y++) {
            for(int x = 0; x < X; x++) {
                arr[y][x] = scan.read() - '0';
            }
            scan.read(); // \n read
        }

        int count = 0;
        for(int x = X - 1; x >= 0; x--) {
            for(int y = Y - 1; y >= 0; y--) {
                if(arr[y][x] == 1) {
                    flip(arr, 0, 0, x, y);
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
