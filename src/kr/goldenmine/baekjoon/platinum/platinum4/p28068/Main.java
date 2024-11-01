package kr.goldenmine.baekjoon.platinum.platinum4.p28068;

import java.util.Arrays;
import java.util.Comparator;

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

    static class Book {
        int minus;
        int plus;

        Book(int minus, int plus) {
            this.minus = minus;
            this.plus = plus;
        }

        @Override
        public String toString() {
            return "\nBook{" +
                    "minus=" + minus +
                    ", plus=" + plus +
                    "}";
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        Book[] books = new Book[N];
        for(int i = 0; i < N; i++) {
            books[i] = new Book(scan.nextInt(), scan.nextInt());
        }
/*
세번째까지 14
5
0 2
0 2
0 10
10 0
5 1

10
0 2
3 4
2 21
10 8
5 1
13 12
11 10
14 13
15 12
15 10
14 10

5
0 10

 */
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                // 일단 증가하는 걸 앞으로, 감소하는걸 뒤로
                int o1Plus = o1.plus - o1.minus;
                int o2Plus = o2.plus - o2.minus;

                if(o1.plus == o2.plus && o1.minus == o2.minus) {
                    return 0;
                }

                // o1만 증가
                if(o1Plus > 0 && o2Plus < 0) {
                    return -1;
                }
                // o2만 증가
                if(o1Plus < 0 && o2Plus > 0) {
                    return 1;
                }
                // 둘다 증가하는 경우 감소수치가 작은걸 앞으로
                if(o1Plus > 0 && o2Plus > 0) {
                    if(o1.minus < o2.minus) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                // 둘다 감소하는 경우 총 감소량이 작은걸 앞으로
                if(o1Plus < 0 && o2Plus < 0) {
//                    if(o2Plus != o1Plus) {
//                        return o2Plus - o1Plus;
//                    } else {
                        return o2.plus - o1.plus;
//                    }
//                    if(o1.minus != o2.minus) {
//                       return o2.minus - o1.minus;
//                    }
//                    if(o1.minus > o2.minus) {
//                        return o1.minus - o2.minus;
//                    }
//                    if(o1.minus < o2.minus) {
//                        return o2.minus - o1.minus;
//                    }
                    // minus는 같음
//                    if(o2Plus == o1Plus) {
//                        return o2.minus - o1.minus;
//                    } else {
//                    return o2.plus - o1.plus;
//                    }
                }
                if(o1Plus == 0 && o2Plus > 0) {
                    return 1;
                }
                if(o1Plus == 0 && o2Plus < 0) {
                    return -1;
                }
                if(o1Plus > 0 && o2Plus == 0) {
                    return -1;
                }
                if(o1Plus < 0 && o2Plus == 0) {
                    return 1;
                }
                return 0;
            }
        });

//        System.out.println(Arrays.toString(books));

        long current = 0;
        for(int i = 0; i < N; i++) {
            Book book = books[i];
            if(current - book.minus < 0) {
                System.out.println(0);
                return;
            }
            current -= book.minus;
            current += book.plus;
//            System.out.println(book + ": " + current);
        }
        System.out.println(1);
    }
}
