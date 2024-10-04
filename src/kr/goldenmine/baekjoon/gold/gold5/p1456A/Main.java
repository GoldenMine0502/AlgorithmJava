package kr.goldenmine.baekjoon.gold.gold5.p1456A;

//import jdk.internal.HotSpotIntrinsicCandidate;

import java.util.Arrays;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32) ; // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char) c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) ;
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -12345;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            } else if (c == 46) {
                c = read();
            }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) {
                while (isNumber(c = read())) {
                    n += (c - 48) / (div *= 10);
                }
            }
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c) {
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
        int n = 1000_0000;
        boolean[] isPrime = new boolean[n + 1];

        // 0과 1은 소수가 아니므로 false, 나머지는 true로 초기화
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // 에라토스테네스의 체 알고리즘 적용
        for (int i = 2; i * i<= n; i++) {
            if (isPrime[i]) { // i가 소수인 경우
//                if(!multiplyExact(i, i)) continue;
                for (int j = i * i; j <= n; j += i) { // i의 배수들은 소수가 아니므로 false로 설정
                    isPrime[j] = false;
                }
            }
        }

        Reader scan = new Reader();

        long A = scan.nextLong();
        long B = scan.nextLong();

        /*
// 1 100000000000000 670121
// 1 100_000_000_000_000
        2 4 8 16 32 64 128
        3 9 27 81 243
        5 25 125 625
        7 49 343
         */
//        HashSet<Long> set = new HashSet<>();
        long count = 0;
        for (long i = 2; i <= n; i++) {
            if (!isPrime[(int) i]) continue;

            long num = i;
            while(num <= B / i) { // num * i <= b
                if(A <= num * i) count++;
                num *= i;
            }
//            for (long j = i * i; j <= B; j *= i) { // n제곱 구하기
//                if (A <= j) {
////                    System.out.println(i + ", " + j + ", " + set.contains(j));
//                    count++;
//                }
//            }
        }
        System.out.println(count);
//        Math.multiplyExact(1L, 1L);
//        Math.multiplyExact(1, 1);
    }

//    public static boolean multiplyExact(int x, int y) {
//        long r = (long)x * (long)y;
//        if ((int)r != r) {
//            return false;
//        }
//        return true;
//    }
//
//
//    public static boolean multiplyExact(long x, long y) {
//        long r = x * y;
//        long ax = Math.abs(x);
//        long ay = Math.abs(y);
//        if (((ax | ay) >>> 31 != 0)) {
//            // Some bits greater than 2^31 that might cause overflow
//            // Check the result using the divide operator
//            // and check for the special case of Long.MIN_VALUE * -1
//            if (((y != 0) && (r / y != x)) ||
//                    (x == Long.MIN_VALUE && y == -1)) {
//                return false;
//            }
//        }
//        return true;
//    }
}

