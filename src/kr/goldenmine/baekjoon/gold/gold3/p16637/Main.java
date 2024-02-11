package kr.goldenmine.baekjoon.gold.gold3.p16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    static class Calculator {
        final String text;
        final LinkedList<Character> list = new LinkedList<>();

        public Calculator(String text) {
            this.text = text;

        }

        public void initList() {
            list.clear();

            for(int i = 0; i < text.length(); i++) {
                list.add(text.charAt(i));
            }
        }

        public double evalNoWrapper() {
            List<Character> stack = new ArrayList<>();
            List<Character> operators = new ArrayList<>();
            for(char ch : list) {
                switch(ch) {
                    case '*': case '/':
                        operators.add(ch);
                        break;
                    default:
                        stack.add(ch);
                        break;
                }
            }

            return 0D;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();


    }
}
