package kr.goldenmine.baekjoon.platinum.platinum5.p21279;

import java.util.*;
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

    static class Mineral {
        int x;
        int y;
        long value;

        Mineral(int x, int y, long value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Mineral{" +
                    "x=" + x +
                    ", y=" + y +
                    ", value=" + value +
                    '}' + '\n';
        }
    }

    static int binarySearch(Mineral[] minerals, int x, int y) {
        int yLeft = 0;
        int yRight = minerals.length;

        while(yLeft < yRight) {
            int mid = (yLeft + yRight) / 2;

            if(y <= minerals[mid].y) {
                yRight = mid;
            } else {
                yLeft = mid + 1;
            }
        }

//        System.out.println(yLeft + ", " + yRight);

        // 이 상황에서 xLeft는 좌표가 x이면서 최솟값이다.
        int xLeft = yLeft;
        int xRight = minerals.length;
        while(xLeft < xRight) {
            int mid = (xLeft + xRight) / 2;

            Mineral mineral = minerals[mid];
            if(y < mineral.y || x < mineral.x) {
                xRight = mid;
            } else {
                xLeft = mid + 1;
            }
        }

        // 하여튼 y x순서로 lowerbound 리턴함
        return xLeft;
    }

    static final int MAX = 100000;

    // [BaekJoon : 21279번(광부 호석) - Tech Blog](https://dev-splin.github.io/coding%20test/baekjoon/BaekJoon-Java-BruteForce-21279/)
    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int C = scan.nextInt();

        Mineral[] minerals = new Mineral[N];

        for(int i = 0; i < N; i++) {
            minerals[i] = new Mineral(scan.nextInt(), scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(minerals, new Comparator<Mineral>() {
            @Override
            public int compare(Mineral o1, Mineral o2) {
                if (o1.y == o2.y) {
                    return o1.x - o2.x;
                } else {
                    return o1.y - o2.y;
                }
            }
        });

//        System.out.println(Arrays.toString(minerals));

        long[] xSum = new long[MAX + 1];
        int[] xCount = new int[MAX + 1];

        long sum = 0;
        long max = 0;
        int count = 0;
        int y = 0;
        for(int x = MAX; x >= 0; x--) {
            while(count < C && y <= MAX) {
                int currentIndex = binarySearch(minerals, x, y) - 1;
                if(currentIndex < 0) {
                    y++;
                    continue;
                }

                Mineral current = minerals[currentIndex];
//                System.out.print(x + ", " + y + ", " + currentIndex + ", " + current);
                if(y < current.y) {
                    y++;
                    continue;
                }

                for(int mIndex = currentIndex; mIndex >= 0; mIndex--) {
                    Mineral next = minerals[mIndex];
                    if(next.y != y) break;

                    sum += next.value;
                    count++;

                    xSum[next.x] += next.value;
                    xCount[next.x]++;

                }
                if(count <= C) {
                    max = Math.max(max, sum);
                }
//                System.out.println("mid " + x + ", " + y + ", " + count + ", " + sum);
                y++;
            }

            if(count <= C) {
                max = Math.max(max, sum);
            }

            sum -= xSum[x];
            count -= xCount[x];

//            System.out.println("total " + x + ", " + y + ", " + count + ", " + sum);
        }
        System.out.println(max);
    }
}
