package kr.goldenmine.baekjoon.silver.silver4.p28279;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {
    static class Reader {
        final int SIZE = 1 << 18;
        byte[] buffer = new byte[SIZE];
        int index, size;

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

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class Writer extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;

        char[] bufForWriteNumber = new char[24];

        Writer() {
            this(1 << 13);
        }

        Writer(int size) {
            super(new OutputStreamWriter(System.out), size);
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 12 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 12; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeLong(long n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 22 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 22; d++) {
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

        public void newLine() throws IOException {
            write('\n');
        }

        @Override
        public void flush() throws IOException {
            super.write(buf, 0, index);
            index = 0;
            super.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int N = scan.nextInt();

        /*
1 X: 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
2 X: 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
3: 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
4: 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
5: 덱에 들어있는 정수의 개수를 출력한다.
6: 덱이 비어있으면 1, 아니면 0을 출력한다.
7: 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
8: 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
         */
        for(int i = 0; i < N; i++) {
            int command = scan.nextInt();

            switch(command) {
                case 1: {
                    int n = scan.nextInt();
                    deque.addFirst(n);
                    break;
                }
                case 2: {
                    int n = scan.nextInt();
                    deque.addLast(n);
                    break;
                }
                case 3: {
                    if(!deque.isEmpty()) {
                        int n = deque.pollFirst();
                        w.writeInt(n);
                        w.newLine();
                    } else {
                        w.writeInt(-1);
                        w.newLine();
                    }
                    break;
                }
                case 4: {
                    if(!deque.isEmpty()) {
                        int n = deque.pollLast();
                        w.writeInt(n);
                        w.newLine();
                    } else {
                        w.writeInt(-1);
                        w.newLine();
                    }
                    break;
                }
                case 5: {
                    w.writeInt(deque.size());
                    w.newLine();
                    break;
                }
                case 6: {
                    w.writeInt(deque.isEmpty() ? 1 : 0);
                    w.newLine();
                    break;
                }
                case 7: {
                    if(!deque.isEmpty()) {
                        int n = deque.getFirst();
                        w.writeInt(n);
                        w.newLine();
                    } else {
                        w.writeInt(-1);
                        w.newLine();
                    }
                    break;
                }
                case 8: {
                    if(!deque.isEmpty()) {
                        int n = deque.getLast();
                        w.writeInt(n);
                        w.newLine();
                    } else {
                        w.writeInt(-1);
                        w.newLine();
                    }
                    break;
                }
            }
        }
        w.close();
    }
}
