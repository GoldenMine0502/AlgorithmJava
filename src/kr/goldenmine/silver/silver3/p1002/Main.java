package kr.goldenmine.silver.silver3.p1002;

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
        while(T-- > 0) {
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int r1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();
            int r2 = scan.nextInt();

            int result = solve(x1, y1, r1, x2, y2, r2);
            System.out.println(result);
        }
    }

    public static int solve(int x1, int y1, int r1, int x2, int y2, int r2) {
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

        int sub = Math.abs(r1 - r2);

        // if (distance == 0 && r1 == r2) { // 두 원이 동심원인 경우 무한대
        if(d > r1 + r2) {
            return 0;
        } else if(d == r1 + r2){
            return 1;
        } else if(sub < d && d < r1 + r2) {
            return 2;
        } else if(d < sub) {
            return 0;
        } else if(sub == 0 && d == 0){
            return -1;
        } else if(d == sub) {
            return 1;
        }


        return 3; //
    }
}
