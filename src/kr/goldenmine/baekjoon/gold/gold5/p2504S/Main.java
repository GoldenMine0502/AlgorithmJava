package kr.goldenmine.baekjoon.gold.gold5.p2504S;

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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String text = scan.nextString();

        Stack<Integer> stack = new Stack<>();

        int OPEN_BRACKET_1 = -1;
        int OPEN_BRACKET_2 = -2;

        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if(ch == '(') {
                stack.push(OPEN_BRACKET_1);
            }

            if(ch == '[') {
                stack.push(OPEN_BRACKET_2);
            }

            if(ch == ')' || ch == ']') {
                int value = 0;
                boolean closed = false;
                while(!stack.isEmpty()) {
                    int pop = stack.pop();
                    if(pop == OPEN_BRACKET_1 && ch == ')') {
                        if(value == 0) value++;
                        value *= 2;
                        closed = true;
                        break;
                    } else if(pop == OPEN_BRACKET_2 && ch == ']') {
                        if(value == 0) value++;
                        value *= 3;
                        closed = true;
                        break;
                    } else if(pop != OPEN_BRACKET_1 && pop != OPEN_BRACKET_2){
                        value += pop;
                    } else {
                        break;
                    }
                }
                if(!closed) {
                    System.out.println(0);
                    return;
                }
                stack.push(value);
            }
//            System.out.println(stack);
        }

        int sum = 0;
        while(!stack.isEmpty()) {
            int v = stack.pop();
            if(v == OPEN_BRACKET_1 || v == OPEN_BRACKET_2) {
                System.out.println(0);
                return;
            }
            sum += v;
        }
        System.out.println(sum);
    }
}
