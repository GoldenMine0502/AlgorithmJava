package kr.goldenmine.baekjoon.gold.gold2.p14476S;

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

    public static int gcd(int a, int b) {
        while(b != 0) {
            int gcd = a % b;
            a = b;
            b = gcd;
        }

        return a;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        int[] arr = new int[N + 1];
        int[] gcds = new int[N + 1];
        int[] gcds_reverse = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = scan.nextInt();
            if(i != 1) {
                gcds[i] = gcd(gcds[i - 1], arr[i]);
            } else {
                gcds[i] = arr[1];
            }
        }

        for(int i = N; i >= 1; i--) {
            if(i == N) {
                gcds_reverse[N] = arr[N];
            } else {
                gcds_reverse[i] = gcd(gcds_reverse[i + 1], arr[i]);
            }
        }

        int max = 0;
        int removedIndex = -1;
        for(int i = 1; i <= N; i++) {
            int nextValue;
            if(i == 1) {
                nextValue = gcds_reverse[2]; // 2~N번째까지의 최대공약수
            } else if(i == N) {
                nextValue = gcds[N - 1]; //1~N-1번째까지의 최대공약수
            } else {
                nextValue = gcd(gcds[i - 1], gcds_reverse[i + 1]); // N번째까지 제외하고 최대공약수
            }

            if(max < nextValue) {
                removedIndex = i;
                max = nextValue;
            }
        }

        int removedValue = arr[removedIndex];
        if(removedValue % max == 0) { // 나눠 떨어지면 약수
            System.out.println(-1);
        } else { // 나눠 떨어지지 않으면 약수 아님
            System.out.println(max + " " + arr[removedIndex]);
        }
//        System.out.println(Arrays.toString(gcds));
//        System.out.println(Arrays.toString(gcds_reverse));
    }
}
