package kr.goldenmine.contest.inu2024.p4;

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

    static class Compass {
        int x;
        int y;
        int direction;

        Compass(int y, int x, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static void 오른쪽(int[][] map, int X, int Y, int N) {
        for(int x = X + 1; x <= N; x++) {
            map[Y][x]++;
        }
    }

    static void 왼쪽(int[][] map, int X, int Y, int N) {
        for(int x = X - 1; x >= 1; x--) {
            map[Y][x]++;
        }
    }

    static void 오른쪽아래(int[][] map, int X, int Y, int N) {
        for(int x = X + 1; x <= N; x++) {
            for(int y = Y + 1; y <= N; y++) {
                map[y][x]++;
            }
        }
    }

    static void 오른쪽위(int[][] map, int X, int Y, int N) {
        for(int x = X + 1; x <= N; x++) {
            for(int y = Y - 1; y >= 1; y--) {
                map[y][x]++;
            }
        }
    }

    static void 왼쪽위(int[][] map, int X, int Y, int N) {
        for(int x = X - 1; x >= 1; x--) {
            for(int y = Y - 1; y >= 1; y--) {
                map[y][x]++;
            }
        }
    }

    static void 왼쪽아래(int[][] map, int X, int Y, int N) {
        for(int x = X - 1; x >= 1; x--) {
            for(int y = Y + 1; y <= N; y++) {
                map[y][x]++;
            }
        }
    }

    static void 위(int[][] map, int X, int Y, int N) {
        for(int y = Y - 1; y >= 0; y--) {
            map[y][X]++;
        }
    }

    static void 아래(int[][] map, int X, int Y, int N) {
        for(int y = Y + 1; y <= N; y++) {
            map[y][X]++;
        }
    }


    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        Compass[] compasses = new Compass[M];
        for(int i = 0; i < M; i++) {
            compasses[i] = new Compass(scan.nextInt(), scan.nextInt(), scan.nextInt());
        }

        int[][] map = new int[N + 1][N + 1];

        // 1번 = 위
        // 2번 = 오른쪽위
        for(Compass compass : compasses) {
            switch(compass.direction) {
                case 1:
                    위(map, compass.x, compass.y, N);
                    break;
                case 2:
                    오른쪽위(map, compass.x, compass.y, N);
                    break;
                case 3:
                    오른쪽(map, compass.x, compass.y, N);
                    break;
                case 4:
                    오른쪽아래(map, compass.x, compass.y, N);
                    break;
                case 5:
                    아래(map, compass.x, compass.y, N);
                    break;
                case 6:
                    왼쪽아래(map, compass.x, compass.y, N);
                    break;
                case 7:
                    왼쪽(map, compass.x, compass.y, N);
                    break;
                case 8:
                    왼쪽위(map, compass.x, compass.y, N);
                    break;
            }

//            System.out.println("==== res ====");
//            for(int y = 1; y <= N; y++) {
//                for (int x = 1; x <= N; x++) {
//                    System.out.print(map[y][x] + " ");
//                }
//                System.out.println();
//            }
        }

        int max = 0;
        int maxY = 0;
        int maxX = 0;

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                if(max < map[y][x]) {
                    max = map[y][x];
                    maxY = y;
                    maxX = x;
                }
            }
        }
        System.out.println(maxY + " " + maxX);
    }
}
