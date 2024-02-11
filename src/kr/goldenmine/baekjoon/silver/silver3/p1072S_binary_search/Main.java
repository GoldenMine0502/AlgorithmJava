package kr.goldenmine.baekjoon.silver.silver3.p1072S_binary_search;

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

    public static int getPercentage(long X, long Y) {
        return (int)(((double)Y * 100 / X));
    }

    public static long upperBound(int X, int Y) {
        long lo = 0;
        long hi = Long.MAX_VALUE;

        int currentPercentage = getPercentage(X, Y);
        if(currentPercentage >= 99) return -1;

//        int checkPercentage = (int)(((double)Y / X) * 1000);
//        if(checkPercentage >= 995) return -1;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            long mid = (lo + hi) / 2; // 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if (currentPercentage < getPercentage(X + mid, Y + mid)) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int X = scan.nextInt();
        int Y = scan.nextInt();

        long result = upperBound(X, Y);
        System.out.println(result);
    }
}
