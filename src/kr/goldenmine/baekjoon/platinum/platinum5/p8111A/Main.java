package kr.goldenmine.baekjoon.platinum.platinum5.p8111A;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Reader {
        final int SIZE = 1 << 6;
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

    static class Node {
        String current;
        int value;

        Node(String current, int value) {
            this.current = current;
            this.value = value;
        }
    }

    public static String getValue(int N) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node("1", 1)); // 1이 적어도 하나 이상인데 0으로 시작하면 안된다... 그러면 1부터 시작해야 하는거 아닌가

        boolean[] visited = new boolean[20001];

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(current.value % N == 0) {
                return current.current;
            }

            int next1 = ((current.value * 10) % N + 1) % N;
            int next0 = (current.value * 10) % N;

            // 100자리 까지만
            if(current.current.length() >= 100) continue;

            if(!visited[next1]) {
                visited[next1] = true;
                queue.add(new Node(current.current + "1", next1));
            }

            if(!visited[next0]) {
                visited[next0] = true;
                queue.add(new Node(current.current + "0", next0));
            }
        }

        return null;
    }

    static class UnsynchronizedWriter extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;
        Writer out;

        static final int BUFFER_SIZE = 22;
        char[] bufForWriteNumber = new char[BUFFER_SIZE];

        UnsynchronizedWriter(Writer out, int size) {
            super(out);
            this.out = out;
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            int d;
            for(d = BUFFER_SIZE - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            for(; d < BUFFER_SIZE; d++) {
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        int T = scan.nextInt();

        UnsynchronizedWriter w = new UnsynchronizedWriter(new OutputStreamWriter(System.out), 1 << 10);

        while(T-- > 0) {
            int N = scan.nextInt();
            String result = getValue(N);
            w.writeString(result != null ? result : "BRAK");
            w.write('\n');
        }
        w.flush();
    }
//    public static void main(String[] args) {
//        FastReader scan = new FastReader();
//
//        int T = scan.nextInt();
//        for(int i = 0; i < T; i++) {
//            int N = scan.nextInt();
//
//
//        }
//    }
}
