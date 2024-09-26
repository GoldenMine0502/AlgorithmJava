package kr.goldenmine.baekjoon.gold.gold1.p20667;

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

    static class Chrome {
        int cpu;
        int memory;
        int priority;

        Chrome(int cpu, int memory, int priority) {
            this.cpu = cpu;
            this.memory = memory;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int C = scan.nextInt();
        int M = scan.nextInt();

        Chrome[] chromes = new Chrome[N + 1];
        for(int i = 1; i <= N; i++) {
            chromes[i] = new Chrome(scan.nextInt(), scan.nextInt(), scan.nextInt());
        }

        // map[0][i] = i번째에서 최소 메모리 사용량
        // map[1][i] = i번째에서 최소 cpu 사용량
//        int[][] map = new int[2][]

        // dp[N][C][P] = memory usage
        int[][][] dp = new int[N + 1][C + 1][500 + 1];
        for(int k = 1; k <= M + 5; k++) {
            for(int n = 1; n <= N; n++) {

            }
        }
    }
}
