package kr.goldenmine.contest.c984_div3.F;

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

    // 1부터 x까지의 XOR을 계산하는 함수 (long 타입 지원)
    public static long xorUpto(long x) {
        long remainder = x % 4L;
        if(remainder == 0) return x;
        if(remainder == 1) return 1;
        if(remainder == 2) return x + 1;
        if(remainder == 3) return 0;
        return 0;
    }

    // N부터 M까지의 XOR을 계산하는 함수 (long 타입 지원)
    public static long rangeXor(long N, long M) {
        return xorUpto(M) ^ xorUpto(N - 1);
    }

    // N부터 M까지 x % (2^I) == K인 숫자들의 XOR을 빠르게 계산하는 함수
    public static long excludedXor(long N, long M, long I, long K) {
        long mask = 1L << I; // 2^I 비트 마스크 생성

        // 범위 내에서 x % mask == K를 만족하는 첫 번째 값과 마지막 값 찾기
        long start = (N + mask - 1 - K) / mask * mask + K;
        long end = (M - K) / mask * mask + K;

        if (start > M || end < N) {
            return 0; // 범위 내에 x % mask == K인 숫자가 없는 경우
        }

        // 패턴에 따라 XOR을 계산
        long xorExcluded = start;
        if (start != end) {
            xorExcluded ^= end;
        }

        return xorExcluded;
    }

    // N부터 M까지의 XOR에서 x % (2^I) == K인 숫자를 제외한 XOR을 계산하는 함수
    public static long rangeXorExcludingPattern(long N, long M, long I, long K) {
        long totalXor = rangeXor(N, M);         // 전체 범위의 XOR
        long excludeXor = excludedXor(N, M, I, K); // 제외할 숫자들의 XOR
        return totalXor ^ excludeXor;           // 제외 후 남은 숫자들의 XOR
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int T = scan.nextInt();
        while(T-- > 0) {
            long l = scan.nextLong();
            long r = scan.nextLong();
            long i = scan.nextLong();
            long k = scan.nextLong();

            long res = rangeXorExcludingPattern(l, r, i, k);
            System.out.println(res);
            // 1  = 0001
            // 2  = 0010
            // 3  = 0011
            // 4  = 0100
            // 5  = 0101
            // 6  = 0110
            // 7  = 0111
            // 8  = 1000
            // 9  = 1001
            // 10 = 1010
            // 11 = 1011

            //  =   00111
            //      01111
        }
    }
}
