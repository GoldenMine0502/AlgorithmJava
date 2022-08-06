package kr.goldenmine.gold.gold2.p1655_priority_queue_mid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
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

        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> bigger = new PriorityQueue<>();

        int N = scan.nextInt() - 1;
        int mid = scan.nextInt();

        StringBuilder sb = new StringBuilder();
        sb.append(mid);
        sb.append("\n");

//        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            int input = scan.nextInt();

            if(input <= mid) {
//                smaller.add(input);
                // 입력이 mid보단 작은데, 그렇다고 smaller의 peek보단 큰 경우
                if(smaller.size() > 0 && input >= smaller.peek()) {
                    // 그럼 input이 중간값이지!!
                    bigger.add(mid);
                    mid = input;
                } else {
                    smaller.add(input);
                }
            } else {
                // 입력이 mid보단 큰데, bigger의 peek보단 작은 경우
                if(bigger.size() > 0 && input < bigger.peek()) {
                    // 그럼 input이 중간값이지!!
                    smaller.add(mid);
                    mid = input;
                } else {
                    bigger.add(input);
                }
            }

            // bigger 쪽이 훨씬 양이 많다면
            // mid -> smaller, bigger -> mid
            if(smaller.size() < bigger.size()) {
                smaller.add(mid);
                mid = bigger.poll();
            }

            // 위와 반대
            if(smaller.size() > bigger.size()) {
                bigger.add(mid);
                mid = smaller.poll();
            }

            sb.append(mid);
            sb.append("\n");
//            System.out.println(mid);
//            System.out.println(Arrays.toString(smaller.toArray()) + ", " + smaller.peek());
//            System.out.println(Arrays.toString(bigger.toArray()) + ", " + bigger.peek());
        }

        System.out.println(sb);
    }
}
