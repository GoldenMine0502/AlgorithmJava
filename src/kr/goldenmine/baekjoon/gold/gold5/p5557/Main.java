package kr.goldenmine.baekjoon.gold.gold5.p5557;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    static class Pair<A, B> {
        A a;
        B b;

        Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }


        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void countRanged(int[] arr, int start, int finish, long[] resultMap) {
        List<Pair<Integer, Long>> nexts = new ArrayList<>();

        resultMap[arr[start]] = 1; // 첫 값 경우 하나
        for(int i = start + 1; i < finish; i++) {
            nexts.clear();
            int current = arr[i];

            for(int j = 0; j <= 20; j++) {
                long before = resultMap[j];
                if(before == 0) continue;

                int nextPlus = j + current;
                if(0 <= nextPlus && nextPlus <= 20) {
                    nexts.add(new Pair<>(nextPlus, before));
                }

                int nextMinus = j - current;
                if(0 <= nextMinus && nextMinus <= 20) {
                    nexts.add(new Pair<>(nextMinus, before));
                }
            }

            for(int j = 0; j <= 20; j++) {
                resultMap[j] = 0;
            }
            for(Pair<Integer, Long> next : nexts) {
                resultMap[next.a] += next.b;
            }
//            System.out.println(nexts);
//            System.out.println(Arrays.toString(resultMap));
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        int N = scan.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        long[] bufferLeft = new long[21]; // 0~20 사이 경우의 수
        int mid = N - 1; // 등호는 마지막에만 나온다
        countRanged(arr, 0, mid, bufferLeft);

        System.out.println(bufferLeft[arr[N - 1]]);
//        System.out.println(Arrays.toString(bufferLeft));
//        System.out.println(Arrays.toString(bufferRight));
    }
}
