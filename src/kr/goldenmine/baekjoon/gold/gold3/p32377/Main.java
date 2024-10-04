package kr.goldenmine.baekjoon.gold.gold3.p32377;

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

    public static long lowerBound(int N, int A, int B, int C) {
        long left = 0;
        long right = 100_000_000_000L;

        // lo가 hi랑 같아질 때 까지 반복
        while (left < right) {
            long mid = (left + right) / 2;
            long estimated = mid / A + mid / B + mid / C;
//            System.out.println(left + ", " + right + ", " + estimated);

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (N <= estimated) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static long upperBound(int N, int A, int B, int C) {
        long lo = 0;
        long hi = 100_000_000_000L;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            long mid = (lo + hi) / 2; // 중간위치를 구한다.
            long value = mid / A + mid / B + mid / C;

            // key값이 중간 위치의 값보다 작을 경우
            if (N < value) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    // 내림차순 기준
    // 완벽하게 같은 값 찾을 때
    public static long binarySearch(int N, int A, int B, int C) {
        long left = 0;
        long right = 100_000_000_000L;


        while(true) {
            long mid = (left + right) / 2;

            long estimated = mid / A + mid / B + mid / C;
            if(estimated > N) { // 더 많이 터뜨림
                right = mid - 1;
            } else if(estimated == N) {
                return mid;
            } else {
                left = mid + 1;
            }

            if(left > right) return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

//        for(int mid = 1; mid <= 20; mid++) {
//            System.out.println(mid + ": " + (mid / A + mid / B + mid / C) + ", " + (mid / A) + ", " + (mid / B) + ", " + (mid / C));
//        }

        long time = lowerBound(N, A, B, C);
//        System.out.println(time);

//        System.out.println(lowerBound(N, A, B, C) + ", " + lowerBound(N - 1, A, B, C) + ", " + lowerBound(N - 2, A, B, C));

        long ap = time / A;
        long bp = time / B;
        long cp = time / C;

        long res = ap + bp + cp;

//        System.out.println(ap + ", " + bp + ", " + cp);

        if(N == res) {
            if(time % cp == 0) {
                System.out.println("C win");
            } else if(time % bp == 0) {
                System.out.println("B win");
            } else {
                System.out.println("A win");
            }
        } else if(N + 1 == res) {
            if(time % cp == 0) {
                System.out.println("B win");
            } else if(time % bp == 0) {
                System.out.println("A win");
            } else {
                System.out.println("C win");
            }
        } else {
            if(time % cp == 0) {
                System.out.println("A win");
            } else if(time % bp == 0) {
                System.out.println("C win");
            } else {
                System.out.println("B win");
            }
        }

//        if(N + 2 == res) {
//            System.out.println("C win");
//        } else if(N + 1 == res) {
//            System.out.println("B win");
//        } else {
//            System.out.println("A win");
//        }
    }
}
