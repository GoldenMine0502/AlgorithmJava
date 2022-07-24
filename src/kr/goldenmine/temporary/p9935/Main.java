package kr.goldenmine.temporary.p9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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

        String text = scan.nextLine();
        String tnt = scan.nextLine();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        List<Character> buffer = new ArrayList<>();

        for(int textIndex = 0; textIndex < text.length(); textIndex++) {
            char ch = text.charAt(textIndex);

            stack.addLast(ch);

            int index = tnt.length() - 1;
            while(index >= 0 && buffer.size() >= tnt.length()) {
                char censorOne = tnt.charAt(index);
                char bufferOne = buffer.get(buffer.size() - tnt.length() + index);

                index--;
                if(censorOne != bufferOne) break;
            }

            if(index == -1) { // 검열 성공
                for(int i = 0; i < tnt.length(); i++) {
                    buffer.remove(buffer.size() - 1);
                }

                while(buffer.size() > 0) {
                    stack.addFirst(buffer.remove(buffer.size() - 1));
                }
                break;
            }
        }
    }
}
