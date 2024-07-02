package kr.goldenmine.templates;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FastOutputs {
    public static void main(String[] args) throws IOException {
        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        // 예시 출력
        for(int i = 0; i < 1000; i++) {
            output.write("a");
            output.write(" ");
        }

        output.flush();
    }

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class UnsynchronizedWriter extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;
        Writer out;

        char[] bufForWriteNumber = new char[22];

        UnsynchronizedWriter(Writer out, int size) {
            super(out);
            this.out = out;
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            int d;
            for(d = 10 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            for(; d < 10; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeString(String s) throws IOException {
            for(int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for(int i = off; i < off + len; i++) {
                write(cbuf[i]);
            }
        }

        public void write(int c) throws IOException {
            if (index >= nChars) flush();
            buf[index++] = (char) c;
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
}
