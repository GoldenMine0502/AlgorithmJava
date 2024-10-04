package kr.goldenmine.baekjoon.bronze.bronze2.p2745S;

public class Main {
    static class Reader {
        final int SIZE = 1 << 7;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String str = scan.nextString();
        int B = scan.nextInt();

        int num = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if('0' <= ch && ch <= '9') {
                num += ch - '0';
            } else {
                num += ch - 'A' + 10;
            }
            if(i < str.length() - 1)
                num *= B;
//            System.out.println(num);
        }
        System.out.print(num);
    }
}
