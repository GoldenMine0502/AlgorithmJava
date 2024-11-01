package kr.goldenmine.baekjoon.gold.gold4.p17255;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Reader {
        final int SIZE = 1 << 4;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32) ; // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char) c;
        }

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
        String value;

        Point(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String text = scan.nextString();

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(text));

        int count = 0;
        while (!queue.isEmpty()) {
            Point value = queue.poll();

            if (value.value.isEmpty()) {
                count++;
                continue;
            }

            String nextLeft = value.value.substring(1);
            String nextRight = value.value.substring(0, value.value.length() - 1);

            if (nextLeft.equals(nextRight)) {
                queue.add(new Point(nextLeft));
            } else {
                queue.add(new Point(nextLeft));
                queue.add(new Point(nextRight));
            }
        }
        System.out.println(count);
    }
}
