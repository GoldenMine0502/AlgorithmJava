package kr.goldenmine.baekjoon.platinum.platinum2.p2076;

import java.util.Arrays;
import java.util.Comparator;

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

    static class Point {
        int x;
        int y;
        double angle;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.angle = Math.atan2(y, x);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", angle=" + angle +
                    '}' + '\n';
        }
    }

    static long distance(Point a, Point b) {
        return (long) (b.x - a.x) * (b.x - a.x) + (long) (b.y - a.y) * (b.y - a.y);
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        Point[] points = new Point[N * 2];
        for(int i = 0; i < N; i++) {
            points[i] = new Point(scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(points, 0, N, new Comparator<Point>() {
            final double EPSILON = 1e-9;

            @Override
            public int compare(Point o1, Point o2) {
                if(Math.abs(o1.angle - o2.angle) < EPSILON) {
                    return 0;
                } else if(o1.angle < o2.angle) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });


        for(int i = 0; i < N; i++) {
            points[i + N] = new Point(points[i].x, points[i].y);
            points[i + N].angle += Math.PI * 2;
        }

        System.out.println(Arrays.toString(points));

        N *= 2;

        Point[] sums = new Point[N + 1];
        sums[0] = new Point(0, 0);
        for(int i = 1; i <= N; i++) {
            Point prev = sums[i - 1];
            Point curr = points[i - 1];
            sums[i] = new Point(prev.x + curr.x, prev.y + curr.y);
        }

        int s = 1;
        int f = 1;
        long max = 0;
        while(s <= N && f <= N) {
//            long previous = distance(sums[s - 1], sums[f - 1]);
            long distance = distance(sums[s - 1], sums[f]);

            double angle = points[f - 1].angle - (s >= 2 ? points[s - 2].angle : 0);

//            System.out.println(previous + ", " + distance);
//            System.out.println(points[f - 1]);

            if(angle > Math.PI) { // 거리가 작아진 경우
                s++;
            } else {
                max = Math.max(max, distance);
                f++;
            }
        }
        System.out.println(max);
    }
}
