package kr.goldenmine.baekjoon.gold.gold4.p9082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

            int[][] arr = new int[2][N];
            String text = scan.next();
            for(int i = 0; i < N; i++) {
                arr[0][i] = text.charAt(i) - '0';
            }
            String line = scan.next();
            for(int i = 0; i < N; i++) {
                arr[1][i] = line.charAt(i);
            } // 여기까지 입력

            int c = 0;
            for(int i = 0; i < N; i++) {
                // 현재 지뢰 개수
                int count = 0;
                for(int j = i - 1; j <= i + 1; j++) {
//                    System.out.println(j);
                    if(j >= 0 && j < N) {
                        if(arr[0][j] != 0) {
                            count++;
                        }
                    } else {
                        count++;
                    }
                }
                if(count == 3) {
                    for(int j = i - 1; j <= i + 1; j++) {
                        if(j >= 0 && j < N) {
                            arr[0][j]--;
                        }
                    }
                    c++;
                }
//                System.out.println(count + ", " + Arrays.toString(arr[1]));
            }
            System.out.println(c);
            // 22222
            // ** **
            // 21222122
            // 가장자리에서 2 = 1,2 n, n-1은 무조건 있다.
            // 가장자리 제외 3 = k-1, k, k+1에 무조건 있다.
            // 1 = 셋 중 한 군데에 있다.
            // 0 = k -1, k, k+1에       무조건 없다.
        }
    }
}
