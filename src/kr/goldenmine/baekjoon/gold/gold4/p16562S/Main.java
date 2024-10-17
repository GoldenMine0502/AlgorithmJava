package kr.goldenmine.baekjoon.gold.gold4.p16562S;

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

    public static int find(int[] parent, int value) {
        if(parent[value] == value)
            return value;

        return parent[value] = find(parent, parent[value]); // path compression
    }

    public static void setMinimum(int[] parent, int[] price, int value) {
        if(parent[value] == value)
            return;

        price[value] = Math.min(price[value], price[find(parent, parent[value])]);
    }

    // by rank
    public static void union(int[] parent, int[] rank, int[] price, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(rank[x] < rank[y]) {
            parent[x] = y;
            price[x] = Math.min(price[x], price[y]);
            rank[y] += rank[x];
        } else {
            parent[y] = x;
            price[y] = Math.min(price[x], price[y]);
            rank[x] += rank[y];
        }
    }

    public static boolean isUnion(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        return x == y;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();
        int K = scan.nextInt();

        int[] price = new int[N + 1];
        int[] parent = new int[N + 1];
        int[] rank = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            price[i] = scan.nextInt();
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i = 1; i <= M; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            union(parent, rank, price, x, y);
        }

//        System.out.println(Arrays.toString(price));
//        System.out.println(Arrays.toString(parent));
//        System.out.println(Arrays.toString(rank));

        for(int i = 1; i <= N; i++) {
            int p = find(parent, i);

            price[p] = Math.min(price[p], price[i]);
        }

        boolean[] count = new boolean[N + 1];
        int sum = 0;
        for(int i = 1; i <= N; i++) {
            int p = find(parent, i);

            if(!count[p]) {
                count[p] = true;
                sum += price[p];
            }
        }

        if(sum <= K) {
            System.out.println(sum);
        } else {
            System.out.println("Oh no");
        }
    }
}
