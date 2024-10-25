package kr.goldenmine.baekjoon.platinum.platinum2.p1227;

import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static class Reader {
        final int SIZE = 1 << 10;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
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

    static class Point {
        short x;
        short y;
        int depth;

        Point(short x, short y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static final short MILLION = 1000;

    // -1 = not visited
    // -2 = blocked
    static private int[][] mapY = new int[MILLION * 2 + 1][2]; // [y좌표][(-1000 or 1000)]
    static private int[][] mapX = new int[MILLION * 2 + 1][2]; // [x좌표][(-1000 or 1000)]
    static private short[][] visited = new short[MILLION * 2 / 4 + 1][MILLION * 2 / 4 + 1];

    static {
        for (int d = 0; d < MILLION * 2 + 1; d++) {
            mapY[d][0] = -1;
            mapY[d][1] = -1;
            mapX[d][0] = -1;
            mapX[d][1] = -1;
        }
    }

    static void set(short x, short y, int value) {
        if(x == -1000) {
            mapY[y + MILLION][0] = value;
        } else if(x == 1000) {
            mapY[y + MILLION][1] = value;
        } else if(y == -1000) {
            mapX[x + MILLION][0] = value;
        } else if(y == 1000) {
            mapX[x + MILLION][1] = value;
        }
    }


    static int get(short x, short y) {
        if(x == -1000) {
            return mapY[y + MILLION][0];
        } else if(x == 1000) {
            return mapY[y + MILLION][1];
        } else if(y == -1000) {
            return mapX[x + MILLION][0];
        } else if(y == 1000) {
            return mapX[x + MILLION][1];
        }
        return -1;
    }

    static boolean getVisited(short x, short y) {
        // int는 32개 쓸 수 있다
        x += MILLION;
        y += MILLION;
        int smallY = y & 0x00000003;
        int smallX = x & 0x00000003;
        short digit = (short) ((1 << ((smallY << 2) + smallX)));
        return (visited[y >> 2][x >> 2] & digit) != 0;
//            return true;
    }

    static void setVisited(short x, short y) {
        x += MILLION;
        y += MILLION;
        int smallY = y & 0x00000003;
        int smallX = x & 0x00000003;
        short digit = (short) ((1 << ((smallY << 2) + smallX)));
        visited[y >> 2][x >> 2] |= digit;
//            System.out.println(x + ", " + y + ", " + smallX + ", " + smallY + ", " + digit + ", " + visited[y >> 2][x >> 2]);
//            visited[y + MILLION][x + MILLION] = true;
    }

    static Point[] directions = new Point[]{
            new Point((short) 1, (short) 0, 0),
            new Point((short) -1, (short) 0, 0),
            new Point((short) 0, (short) 1, 0),
            new Point((short) 0, (short) -1, 0)
    };

    static long odd = 0;
    static long even = 0;

    static void bfs(int S) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point((short) 0, (short) 0, 0));
        setVisited((short) 0, (short) 0);

        int count = 0;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            set(p.x, p.y, p.depth);
            count++;

            if(count % 500000 == 0) {
                System.gc();
            }

            if (p.depth % 2 == 0) {
                even++;
            } else {
                odd++;
            }

            if (p.depth >= S) continue;

            for (Point direction : directions) {
                short nextX = (short) (p.x + direction.x);
                short nextY = (short) (p.y + direction.y);
                if (nextX < -MILLION || nextX > MILLION || nextY < -MILLION || nextY > MILLION) continue;

                Point next = new Point(nextX, nextY, p.depth + 1);

                if (!getVisited(nextX, nextY)) {
                    setVisited(nextX, nextY);

                    queue.add(next);
                }
            }
//            System.gc();
        }
    }

    static void diagonal(int value, int S) {
        if (value <= 0) return;

        long diff1 = (S - value + 1) / 2;
        long v1 = diff1 * diff1 + diff1;
        long diff2 = (S - value) / 2;
        long v2 = diff2 * diff2 + 2 * diff2;

        // 1 2 3 4
        // s(0) = 0 + 0
        // s(1) = 2 + 0
        // s(2) = 2 + 3
        // s(3) = (2 + 4) + 3
        // s(4) = (2 + 4) + (3 + 5) = 6 + 8

//        System.out.println(v1 + ", " + v2);

        if (value % 2 == 0) {
            odd += v1;
            even += v2;
        } else {
            even += v1;
            odd += v2;
        }
    }

    static void straight(int value, int S) {
        if(value <= 0) return;

        int v1 = (S - value + 1) / 2;
        int v2 = (S - value) / 2;
        if (value % 2 == 0) {
            odd += v1;
            even += v2;
        } else {
            even += v1;
            odd += v2;
        }
    }

    public static void main(String[] args) throws Exception {
//        getDiagonal(3, 6);
//        straight(4, 9);
//        getDiagonal(4, 6);
//        getDiagonal(5, 6);
//        getDiagonal(6, 6);
//        System.out.println(odd + " " + even);
//        System.out.println(33 & 0x0000001F);
//        Map map = new Map();
//        map.setVisited(1, 0);
//        map.setVisited(1, 1);
//        map.setVisited(0, 0);
//        map.setVisited(3, 3);

        Reader scan = new Reader();

        int M = scan.nextInt();
        int S = scan.nextInt();

        for (int i = 0; i < M; i++) {
            short y = (short) scan.nextInt();
            short x = (short) scan.nextInt();

            setVisited(x, y);
        }

        bfs(S);
//        memory();

        diagonal(get((short) -1000, (short) -1000), S);
        diagonal(get((short) 1000, (short) 1000), S);
        diagonal(get((short) -1000, (short) 1000), S);
        diagonal(get((short) 1000, (short) -1000), S);
        for (short d = -999; d <= 999; d++) {
            straight(get((short) -1000, d), S);
            straight(get((short) 1000, d), S);
            straight(get(d, (short) -1000), S);
            straight(get(d, (short) 1000), S);
        }

        System.out.println(even + " " + odd);
//        memory();
    }

    static void memory() {
        // JVM이 사용 가능한 총 메모리 중 할당된 양
        long totalMemory = Runtime.getRuntime().totalMemory();

        // 현재 JVM에서 사용 가능한 여유 메모리 양
        long freeMemory = Runtime.getRuntime().freeMemory();

        // 사용된 메모리 양: totalMemory에서 freeMemory를 뺀 값
        long usedMemory = totalMemory - freeMemory;

        // MB 단위로 출력
        System.out.println("Used memory (MB): " + (usedMemory / (1024 * 1024)) + " MB");
    }
}
