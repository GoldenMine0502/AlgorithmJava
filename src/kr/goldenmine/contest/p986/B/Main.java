package kr.goldenmine.contest.p986.B;

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

    public static long upperBound(long A, long D, long N) {
        long lo = 0;
        long hi = Long.MAX_VALUE;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            long mid = (lo + hi) / 2; // 중간위치를 구한다.

            long metric = (N + D - 1 - A) / D;
            // key값이 중간 위치의 값보다 작을 경우
            if (metric < mid) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int T = scan.nextInt();
        while(T-- > 0) {
            long N = scan.nextLong();
            long D = scan.nextLong(); // d
            long A = scan.nextLong(); // a_1

            if(D > 0) {
                if(A <= N) {
                    long res = upperBound(A, D, N) - 1;
                    System.out.println(N - res);
                } else {
                    System.out.println(N);
                }
            } else {
                if(N == 1) {
                    if(A == 0) {
                        System.out.println(0);
                    } else {
                        System.out.println(1);
                    }
                } else if(N == 2) {
                    if(A < N) {
                        System.out.println(1);
                    } else {
                        System.out.println(2);
                    }
                } else {
                    if(A < N) {
                        if(N - A > 2) {
                            System.out.println(-1);
                        } else {
                            System.out.println(N - 1);
                        }
                    } else {
                        System.out.println(N);
                    }
                }
            }
        }
    }
}
