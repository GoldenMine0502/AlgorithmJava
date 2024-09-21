package kr.goldenmine.baekjoon.gold.gold4.p13701;

import java.io.*;
import java.lang.reflect.Field;

public class Main {
    static class Reader {
        final int SIZE = 1 << 20;
        byte[] buffer = new byte[SIZE];
        int index, size;

        // String을 만들지 않기 위해 이짓거리를 해준다
        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (47 < (c = read()) && c < 58);
            return n;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if(size == -1) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class UnsynchronizedWriter extends BufferedOutputStream {
        byte[] buf;
        int nChars;
        int index;
        OutputStream out;

        UnsynchronizedWriter(OutputStream out, int size) {
            super(out, size);
            this.out = out;
            buf = new byte[size];
            nChars = size;
        }

        @Override
        public void write(byte[] cbuf, int off, int len) throws IOException {

        }

        public void write(int c) throws IOException {
            if (index >= nChars) flush();
            buf[index++] = (byte) c;
        }

        @Override
        public void flush() throws IOException {
            out.write(buf, 0, index);
            index = 0;
            out.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
        }
    }

    // PrintStream에도 synchronized가 붙어있으므로 마치 cpp에서 cin.tie(NULL) sync 뭐시기 하는것처럼 동기화를 없애주는 작업
//    static UnsynchronizedWriter reflectPrintStream(int size) {
//        try {
//            // 1. System.out의 PrintStream 객체 얻기
//            PrintStream originalOut = System.out;
//
//            // 2. PrintStream 클래스의 textOut 필드에 접근
//            Field textOutField = PrintStream.class.getDeclaredField("textOut");
//            textOutField.setAccessible(true);
//            BufferedWriter writer = (BufferedWriter) textOutField.get(originalOut);
//
//            // 3. textOut 필드를 새 객체로 바꾸기
//            UnsynchronizedWriter myWriter = new UnsynchronizedWriter(writer, size);
//            textOutField.set(originalOut, myWriter);
//            return myWriter;
//        } catch(Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    public static void main(String[] args) throws Exception {
//        UnsynchronizedWriter w = reflectPrintStream(1 << 16);
        Reader scan = new Reader();

        // Bitset Array
        int[] arr = new int[1 << 20];

        UnsynchronizedWriter w = new UnsynchronizedWriter(System.out, 1 << 15);
        int n;
        int[] digits = new int[10]; // 출력할 숫자는 최대 8자리이므로
        while((n = scan.nextInt()) != -1) {

            if((arr[n >> 5] & (1 << n)) == 0) {
                arr[n >> 5] |= 1 << n;

                // String을 만들지 않기 위해 char배열을 재사용 하는 방향으로 작동한다
                int d;
                for(d = 10 - 1; d >= 0; d--) {
                    digits[d] = n % 10 + '0';
                    n /= 10;
                    if(n == 0) break;
                }

                for(; d < 10; d++) {
                    w.write(digits[d]);
                }
                w.write(' ');
            }
        }
        w.flush();
    }
}
