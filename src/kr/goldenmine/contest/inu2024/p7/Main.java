package kr.goldenmine.contest.inu2024.p7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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

    public static double distance(int x, int y, int x2, int y2) {
        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
    }

    static class Point {
        int x;
        int y;
        double distance;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
            this.distance = distance(0, 0, x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", distance=" + distance +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        double EPSILON = 1e-9;

        Reader scan = new Reader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        Point[] points = new Point[N];
        for(int i = 0; i < N; i++) {
            points[i] = new Point(scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double diff = o1.distance - o2.distance;

                if(diff < 0) {
                    return -1;
                } else if(diff < EPSILON) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });


        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                double diff = o1.distance - o2.distance;

                if(diff < 0) {
                    return -1;
                } else if(diff < EPSILON) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        int max = 0;
        for(Point p : points) {
            queue.add(p);

            double currentDistance = p.distance;

            while(!queue.isEmpty()) {
                Point peek = queue.peek();
                double distance = peek.distance;

                if(currentDistance - K > distance) {
                    queue.poll();
                } else {
                    break;
                }
            }

//            System.out.println(queue);

            max = Math.max(max, queue.size());
        }

        System.out.printf("%.9f", (double) max / N * 100);
    }
}
