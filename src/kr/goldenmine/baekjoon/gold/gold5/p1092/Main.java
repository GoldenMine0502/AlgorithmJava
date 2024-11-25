package kr.goldenmine.baekjoon.gold.gold5.p1092;

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

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) ;
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -12345;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            } else if (c == 46) {
                c = read();
            }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) {
                while (isNumber(c = read())) {
                    n += (c - 48) / (div *= 10);
                }
            }
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c) {
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int CRANE = scan.nextInt();

        Integer[] cranes = new Integer[CRANE];
        for (int i = 0; i < CRANE; i++) {
            cranes[i] = scan.nextInt();
        }

        int BOX = scan.nextInt();
        int[] boxes = new int[BOX];
        for (int i = 0; i < BOX; i++) {
            boxes[i] = scan.nextInt();
        }

        Arrays.sort(cranes);
        Arrays.sort(boxes);



//        System.out.println(Arrays.toString(cranes));
//        System.out.println(Arrays.toString(boxes));

//        int[] craneIndices = new int[CRANE];
//        Arrays.fill(craneIndices, -1);
//        int c = 0;
//        for(int i = 0; i < BOX; i++) {
//            while(true) {
//                if(c == CRANE) {
//                    System.out.println(-1);
//                    return;
//                }
//
//                if(cranes[c] < boxes[i]) {
//                    c++;
//                } else {
//                    break;
//                }
//            }
//            craneIndices[c] = i;
//        }
//        for (int cc = c; cc < CRANE - 1; cc++) {
//            craneIndices[cc + 1] = craneIndices[cc];
//        }
//
//        System.out.println(Arrays.toString(craneIndices));
//
//        int[] tasks = new int[CRANE];
//        for(int i = 0; i < CRANE; i++) {
//            if(i == 0) {
//                tasks[0] = craneIndices[0] + 1;
//            } else {
//                tasks[i] = (craneIndices[i] - craneIndices[i - 1]);
//            }
//        }
//
//        for(int crane = CRANE - 1; crane >= 0; crane--) {
//
//        }
//
//        System.out.println(Arrays.toString(tasks));
    }
}
/*
3
10 6 5
11
5 6 6 6 6 6 6 6 6 7 7

3
10 6 5
8
1 1 1 1 2 2 6 7
 */