package kr.goldenmine.baekjoon.silver.silver1.p1080;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
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

    static void reverse(int[][] map, int Y, int X) {
        for(int y = Y - 1; y <= Y + 1; y++) {
            for(int x = X - 1; x <= X + 1; x++) {
                map[y][x]++;
                map[y][x] %= 2;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] map1 = new int[N][M];
        int[][] map2 = new int[N][M];

        for(int y = 0; y < N; y++) {
            String line = scan.nextString();
            for(int x = 0; x < M; x++) {
                map1[y][x] = line.charAt(x) - '0';
            }
        }

        for(int y = 0; y < N; y++) {
            String line = scan.nextString();
            for(int x = 0; x < M; x++) {
                map2[y][x] = line.charAt(x) - '0';
            }
        }

        int count = 0;
        for(int y = 1; y < N - 1; y++) {
            for(int x = 1; x < M - 1; x++) {
                if(map1[y - 1][x - 1] != map2[y - 1][x - 1]) {
                    reverse(map1, y, x);
                    count++;
                }
            }
        }

        boolean verify = true;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(map1[y][x] != map2[y][x]) {
                    verify = false;
                }
            }
        }

        if(verify) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
