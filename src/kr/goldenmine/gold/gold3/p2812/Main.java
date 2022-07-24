package kr.goldenmine.gold.gold3.p2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        String text = scan.next();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        int deleted = 0;

        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

//            System.out.println(stack);

            while(deleted < K && !stack.isEmpty()) {
                char stackOne = stack.removeLast();
                if(ch > stackOne) { // 스택에서 꺼낸게 넣을 애보다 작으면 제거해야지
                    deleted++;
                } else {
                    stack.addLast(stackOne);
                    break;
                }
            }

            stack.addLast(ch);
        }

        while(deleted < K) {
            stack.removeLast();
            deleted++;
        }

        for(char ch : stack) {
            System.out.print(ch);
        }
    }
}
