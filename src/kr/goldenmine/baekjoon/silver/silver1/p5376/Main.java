package kr.goldenmine.baekjoon.silver.silver1.p5376;

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

    public static void solve(String text) {
        long first = 0;
        long firstDen = 1;
        long second = 0;
        long secondDen = 0;
        boolean mode = false;
        for(int i = 2; i < text.length(); i++) {
            char ch = text.charAt(i);
            if(ch == '(') {
                mode = true;
                continue;
            }
            if(ch == ')') {
                break;
            }

            if(!mode) {
                first *= 10;
                first += (ch - '0');

                firstDen *= 10;
            } else {
                second *= 10;
                second += (ch - '0');
                secondDen *= 10;
                secondDen += 9;
            }
        }

        if(second != 0) {
//            secondDen *= firstDen;

            // ll ans1 = B + A * (l10 - 1), ans2 = k10 * (l10 - 1);
            long a = second + first * (secondDen);
            long b = firstDen * secondDen;

            long gcd = gcd(a, b);

            System.out.println(a / gcd + "/" + b / gcd);
        } else {
            long gcdFirst = gcd(first, firstDen);

            System.out.println(first / gcdFirst + "/" + firstDen / gcdFirst);
        }
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    // 0.3234(1231242)
    // 0.999999(999999)
    // 0.123456(123456789)
    // 0.234567(123456789)
    // 0.2347(9876543)
    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        while(N-- > 0) {
            solve(scan.nextString());
        }
    }
}
