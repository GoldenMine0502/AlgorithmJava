package kr.goldenmine.baekjoon.gold.gold4.p2800;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class Writer extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;

        char[] bufForWriteNumber = new char[22];

        Writer() {
            this(1 << 10);
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
            for(d = 10 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
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

    static List<String> results = new ArrayList<>();

    static String remove(List<String> split, boolean[] visited) {
        split = new ArrayList<>(split);

        Stack<String> result = new Stack<>();
        Stack<String> inner = new Stack<>();

        int c = 0;

        for(int i = 0; i < split.size(); i++) {
            String one = split.get(i);


            if(one.equals(")")) {
                while(!result.isEmpty()) {
                    String pop = result.pop();

                    if(pop.equals("(")) {
                        break;
                    }

                    inner.push(pop);
                }

                StringBuilder total = new StringBuilder();
                if(visited[c++]) { // 괄호 제거
                    while(!inner.isEmpty()) {
                        String pop = inner.pop();
                        total.append(pop);
                    }
                } else { // 괄호 제거 안함
                    total.append("(");
                    while(!inner.isEmpty()) {
                        String pop = inner.pop();
                        total.append(pop);
                    }
                    total.append(")");
                }
                result.push(total.toString());
            } else {
                result.push(one);
            }
//            System.out.println(result);
        }

        StringBuilder finalStr = new StringBuilder();
        for(String s : result) {
            finalStr.append(s);
        }
        return finalStr.toString();
    }

    static void dfs(List<String> split, int n, boolean[] visited, int k) {
        if(n == k) {
            results.add(remove(split, visited));
            return;
        }

        for(int i = k; i < n; i++) {
            visited[i] = true;
            dfs(split, n, visited, i + 1);
            visited[i] = false;
            dfs(split, n, visited, i + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String text = scan.nextString();

        List<String> split = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        int opens = 0;
        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if(ch == '(') {
                opens++;
            }

            if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
                if(sb.length() > 0) {
                    split.add(sb.toString());
                    sb.setLength(0);
                }
                split.add(ch + "");
            } else {
                sb.append(ch);
            }
        }

        if(sb.length() > 0) {
            split.add(sb.toString());
        }

//        System.out.println(split);

        int[] arr = new int[opens];
        boolean[] visited = new boolean[opens];
        for(int i = 0; i < opens; i++) {
            arr[i] = i;
        }
        dfs(split, opens, visited, 0);

        Writer w = new Writer();
        results.stream().sorted().distinct().filter(it -> !it.equals(text)).forEach(it -> {
            try {
                w.writeString(it);
                w.write('\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        w.close();
//        System.out.println(remove(split, visited));
    }
}
