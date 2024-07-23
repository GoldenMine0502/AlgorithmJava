package kr.goldenmine.baekjoon.bronze.bronze1.p2755S;

import java.util.HashMap;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;
        StringBuilder sb = new StringBuilder();

        String nextString() throws Exception {
            sb.setLength(0);
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        HashMap<String, Double> map = new HashMap<>();
        map.put("A+", 4.3);
        map.put("A0", 4.0);
        map.put("A-", 3.7);
        map.put("B+", 3.3);
        map.put("B0", 3.0);
        map.put("B-", 2.7);
        map.put("C+", 2.3);
        map.put("C0", 2.0);
        map.put("C-", 1.7);
        map.put("D+", 1.3);
        map.put("D0", 1.0);
        map.put("D-", 0.7);
        map.put("F", 0.0);

        int N = scan.nextInt();

        int sum = 0;
        double values = 0;
        for(int i = 0; i < N; i++) {
            scan.nextString();
            int n = scan.nextInt();
            String grade = scan.nextString();

//            System.out.println(grade);

            sum += n;
            values += map.get(grade) * n;
        }

        System.out.printf("%.2f", values / sum);
    }
}
