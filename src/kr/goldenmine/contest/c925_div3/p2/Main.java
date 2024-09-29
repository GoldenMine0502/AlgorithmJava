package kr.goldenmine.contest.c925_div3.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int T = scan.nextInt();
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int N = scan.nextInt();

            boolean res = true;

            int[] arr = new int[N];
            int sum = 0;
            for(int i = 0; i < N; i++) {
                arr[i] = scan.nextInt();
                sum += arr[i];
            }
            int goal = sum / N;

            for(int i = 0; i < N - 1; i++) {
                if(arr[i] > goal) {
                    int sub = arr[i] - goal;
                    arr[i] = goal;
                    arr[i + 1] += sub;
                } else if(arr[i] < goal) {
                    res = false;
                    break;
                }
//                System.out.println(T + ", " + Arrays.toString(arr));
            }

            res = res && arr[N - 1] == goal;

            sb.append(res ? "Yes" : "No");
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
