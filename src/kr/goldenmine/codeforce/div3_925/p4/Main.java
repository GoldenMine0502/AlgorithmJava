package kr.goldenmine.codeforce.div3_925.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

        while(T-- > 0) {
            int N = scan.nextInt();
            int X = scan.nextInt();
            int Y = scan.nextInt();

            Integer[] arr = new Integer[N];
            for(int i = 0; i < N; i++) {
                arr[i] = scan.nextInt();
            }
            Arrays.sort(arr, Comparator.comparingInt(o -> o % X));

            int count = 0;
            // 1 10 15 3 8 12 15
            // 1 0 0 3 3 2 0
            // 0 0 0 1 2 3 3
            int start = 0;
            int finish = N - 1;

            while(start < finish) {
                int sum = arr[start] + arr[finish];
                System.out.println(sum + " , " + start + ", " + finish);
                if(sum % X == 0 && (arr[start] - arr[finish] + Y) % Y == 0) {
                    count++;
                }

                int leftDiff = Math.abs(arr[start] % X - arr[start + 1] % X) % X;
                int rightDiff = Math.abs(arr[finish] % X - arr[finish - 1] % X) % X;

                if(leftDiff < rightDiff) {
                    start++;
                } else {
                    finish--;
                }
            }
            System.out.println(count);
        }
    }
}
