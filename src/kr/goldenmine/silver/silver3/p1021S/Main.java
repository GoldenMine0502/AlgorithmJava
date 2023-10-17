package kr.goldenmine.silver.silver3.p1021S;

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
        int M = scan.nextInt();

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            deque.add(i);
        }

        int calculation = 0;
        for(int i = 0; i < M; i++) {
            int VAL = scan.nextInt();

            int size = deque.size();

            int j = 0;
            for(; j < size; j++) {
                int first = deque.getFirst();
                if(VAL != first) {
                    // 무조건 2번 연산
                    deque.removeFirst();
                    deque.addLast(first);
                } else {
                    break;
                }
            }
            if(j > 0) {
                // 3번 연산은 (size - 2번 연산 횟수) 이다.
                int j2 = size - j;

                if(j < j2) { // j가 더 작으므로 2번 연산으로 결정
                    calculation += j;
                } else { // j2가 더 작으므로 3번 연산으로 결정
                    calculation += j2;
                }
//                System.out.println(deque + ", " + j + ", " + j2);
            }
            deque.removeFirst(); // 연산된 결과는 꺼낸다.
        }
        System.out.println(calculation);
    }
}
