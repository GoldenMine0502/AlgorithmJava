package kr.goldenmine.baekjoon.silver.silver2.p16953S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

    static class Node {
        long number;
        int count;

        public Node(long number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "number=" + number +
                    ", count=" + count +
                    '}';
        }
    }

    public static int BFS(int A, int B) {
        Queue<Node> numbers = new LinkedList<>();

        numbers.add(new Node(A, 1));

        int minimum = -1;

        while(!numbers.isEmpty()) {
            Node number = numbers.poll();

//            System.out.println(number);

            if(number.number == B) {
                if(minimum == -1) {
                    minimum = number.count;
                } else {
                    minimum = Math.min(minimum, number.count);
                }
            }

            long nextValue1 = number.number * 10 + 1;
            long nextValue2 = number.number * 2;

            if(nextValue1 <= B) numbers.add(new Node(nextValue1, number.count + 1));
            if(nextValue2 <= B) numbers.add(new Node(nextValue2, number.count + 1));
        }

        return minimum;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int A = scan.nextInt();
        int B = scan.nextInt();

        int result = BFS(A, B);

        System.out.println(result);
    }
}
