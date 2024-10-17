package kr.goldenmine.baekjoon.platinum.platinum5.p17398;

import java.util.HashSet;
import java.util.Set;

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

    static int find(int[] parent, int value) {
        if(parent[value] == value)
            return value;
        return parent[value] = find(parent, parent[value]); // path compression
    }

    // by rank
    static void union(int[] parent, int[] rank, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x == y) return;

        if(rank[x] < rank[y]) {
            parent[x] = y;
            rank[y] += rank[x];
        } else {
            parent[y] = x;
            rank[x] += rank[y];
        }
    }

    static boolean isUnion(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        return x == y;
    }

    static class Node {
        int a;
        int b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();
        int Q = scan.nextInt();

        // union find
        int[] parents = new int[N + 1];
        int[] ranks = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        // 노드 정보
        Node[] nodes = new Node[M + 1];
        for(int i = 1; i <= M; i++) {
            nodes[i] = new Node(scan.nextInt(), scan.nextInt());
        }

        // 지울 인덱스
        int[] removes = new int[Q];
        Set<Integer> removesSet = new HashSet<>();
        for(int i = 0; i < Q; i++) {
            removes[i] = scan.nextInt();
            removesSet.add(removes[i]);
        }

        // 끊음과 관련 없는 노드 연결
        for(int i = 1; i <= M; i++) {
            if(!removesSet.contains(i)) {
                Node node = nodes[i];
                union(parents, ranks, node.a, node.b);
            }
        }

        // 반대에서부터 연결하기
        long cost = 0;
        for(int i = Q - 1; i >= 0; i--) {
            int idx = removes[i];

            Node node = nodes[idx];

            int ap = find(parents, node.a);
            int bp = find(parents, node.b);

            // 같으면 코스트 계산할 필요 없고 union도 필요 없음
            if(ap != bp) {
                int cost1 = ranks[ap];
                int cost2 = ranks[bp];

                cost += cost1 * cost2;

                union(parents, ranks, node.a, node.b);
            }
//            System.out.println(ap + ", " + bp + ", " + cost);
        }

        System.out.println(cost);
    }
}
