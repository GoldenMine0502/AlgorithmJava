package kr.goldenmine.baekjoon.gold.gold2.p20442;

import java.util.Arrays;

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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String text = scan.nextString();

        int[] kLeft = new int[text.length() + 1];
        int[] kRight = new int[text.length() + 1];
        int[] rLeft = new int[text.length() + 1];

        for(int i = 1; i <= text.length(); i++) {
            char ch = text.charAt(i - 1);
            kLeft[i] = kLeft[i - 1] + (ch == 'K' ? 1 : 0);
        }

        for(int i = text.length(); i >= 1; i--) {
            char ch = text.charAt(i - 1);
            kRight[i] = (i < text.length() ? kRight[i + 1] : 0) + (ch == 'K' ? 1 : 0);
        }

        for(int i = 1; i <= text.length(); i++) {
            char ch = text.charAt(i - 1);
            rLeft[i] = rLeft[i - 1] + (ch == 'R' ? 1 : 0);
        }
/*
KKRKK
[0, 1, 2, 2, 3, 4]
[0, 4, 3, 2, 2, 1]

RRKRR
[0, 0, 0, 1, 1, 1]
[0, 1, 1, 1, 0, 0]

 */
//        System.out.println(Arrays.toString(kLeft));
//        System.out.println(Arrays.toString(kRight));
//        System.out.println(Arrays.toString(rLeft));


        int max = 0;
        int left = 1;
        int right = text.length();

        while(left <= right) {
            char leftCh = text.charAt(left - 1);
            char rightCh = text.charAt(right - 1);

            if(leftCh == 'K') {
                left++;
            } else if(rightCh == 'K') {
                right--;
            } else {
                // left right 둘 다 R인 상황
                int Rs = rLeft[right] - rLeft[left - 1];
                int Ks = Math.min(kLeft[left], kRight[right]) * 2;

                max = Math.max(max, Rs + Ks);


//                System.out.println("R: " + Rs + ", K: " + kLeft[left] + ", " + kRight[right] + ": " + left + ", " + right);

                if(kLeft[left] < kRight[right]) {
                    left++;
                } else /*if(kLeft[left] > kRight[right])*/ {
                    right--;
                } /*else {
                    left++;
                    right--;
                }*/
                }
//            System.out.println(left + ", " + right);
        }
        System.out.println(max);
    }
}
