package kr.goldenmine.baekjoon.gold.gold3.p14676S;

import java.util.ArrayList;
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();
        int K = scan.nextInt();

        List<List<Integer>> nodes = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        int[] indegrees = new int[N + 1];

        for(int i = 0; i < M; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            nodes.get(start).add(finish);

            indegrees[finish]++;
        }

        int[] builded = new int[N + 1];

        boolean isLier = false;

        while(K-- > 0) {
            int command = scan.nextInt();
            int index = scan.nextInt();

            if(command == 1) { // 건물 짓기
                if(indegrees[index] > 0) { // 짓는 순간 내 indegree는 무조건 0이어야 함 아니면 치트
                    isLier = true;
                    break;
                }

                if(builded[index] == 0) { // 지은 적이 없으면 건물의 다음 단계에 대한 indegree 감소
                    for(int next : nodes.get(index)) {
                        indegrees[next]--;
                    }
                }
                builded[index]++;
            } else { // 건물 파괴
                builded[index]--;
                if(builded[index] == 0) { // 건물이 이제 없는 상태
                    for(int next : nodes.get(index)) { // 다시 이어서 다음 건물 못짓게 해야된다
                        indegrees[next]++;
                    }
                } if(builded[index] < 0) { // 없는 건물을 깨버림
                    isLier = true;
                    break;
                }
            }
        }

        if(isLier) {
            System.out.println("Lier!");
        } else {
            System.out.println("King-God-Emperor");
        }
    }
}
