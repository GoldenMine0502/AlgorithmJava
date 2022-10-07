package kr.goldenmine.gold.gold4.p2133;

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

        int N = scan.nextInt();
        /*
        1번째 줄일때: 0가지
        2번째 줄일때: 3가지 ||, _

        홀수개로 끝나는건 불가능하다. 왜냐하면 모든 타일의 크기가 짝수 크기이기 때문이다.

        두줄을 사용해서 만드는 경우: 3가지
        네 줄을 사용해서 만드는 경우: 두줄 * 두줄(= 9가지) +

        4번째 줄일때: (2가지 경우 * 2가지 경우) + (4가지 추가 -> 두칸짜리가 중간에 오는 경우 -> 3가지)
         */

        int[] arr = new int[N + 1];
        int two = 3;
        int four = 3;
        for(int i = 0; i < N; i++) {
            if(i % 2 == 1) continue; // 홀수는 불가능하다
        }

        System.out.println(arr[N]);
    }
}
