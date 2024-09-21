package kr.goldenmine.baekjoon.bronze.bronze1.p25643;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;
        StringBuilder sb = new StringBuilder();

        String nextString() throws Exception {
            sb.setLength(0);
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

    public static boolean check(String a, String b) {
        for(int shift = -a.length() + 1; shift < a.length() - 1; shift++) {
            boolean verify = true;
            for(int i = 0; i < b.length(); i++) {
                if (a.charAt(i) != b.charAt(i + shift)) {
                    verify = false;
                    break;
                }
            }

            if(verify) {
                return true;
            }
        }
        return false;
//        return a.charAt(0) == b.charAt(a.length() - 1) || a.charAt(a.length() - 1) == b.charAt(0) || a.equals(b);
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        String[] strings = new String[N];
        for(int i = 0; i < N; i++) {
            strings[i] = scan.nextString();
        }

        for(int i = 0; i < N - 1; i++) {
            String a = strings[i];
            String b = strings[i + 1];

            if(!(check(a, b) || check(new StringBuilder(a).reverse().toString(), new StringBuilder(b).reverse().toString()))) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}
