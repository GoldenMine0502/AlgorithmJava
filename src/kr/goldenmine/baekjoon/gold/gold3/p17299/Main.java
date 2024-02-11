package kr.goldenmine.baekjoon.gold.gold3.p17299;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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

        int[] arr = new int[N];
        int[] counts = new int[1_000_000 + 1];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();

            counts[arr[i]]++;
        }

        int[] results = new int[N];

        // 카운트가 제일 높은 것이 위에 오도록
        Stack<Integer> stack = new Stack<>();

        for(int i = N - 1; i >= 0; i--) {
            int current = arr[i];

            while(!stack.isEmpty()) {
                int value = stack.pop();

                // 이러면 현재 검사하려는 수 보다 스택에 든 숫자의 횟수가 더 많은것
                if(counts[value] > counts[current]) {
                    stack.push(value);
                    break;
                }
            }

            results[i] = !stack.isEmpty() ? stack.peek() : -1;

            stack.push(current);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(results[i]);
            if(i != N - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
//        System.out.println(Arrays.toString(results));
    }
}
