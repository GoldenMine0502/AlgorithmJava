package kr.goldenmine.baekjoon.gold.gold2.p10775;

public class Main {
    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
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

    public static int find(int[] parent, int value) {
        if(parent[value] == value)
            return value;
        return parent[value] = find(parent, parent[value]); // path compression
    }

    // by rank
    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x == y) return;

        // 항상 작은 쪽으로
        if(x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    public static boolean isUnion(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        return x == y;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int G = scan.nextInt();
        int P = scan.nextInt();

        int[] parent = new int[G + 1];

        for(int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < P; i++) {
            int g = scan.nextInt();

            int next = find(parent, g);
            if(next == 0) {
                System.out.println(i);
                return;
            }

//            union(parent, next, next - 1);
            parent[next] = find(parent, next - 1);
        }
        System.out.println(P);
    }
}
