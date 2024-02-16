package kr.goldenmine.codeforce.c926_div2.p1;

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
            int[] arr = new int[N];

            for(int i = 0; i < arr.length; i++) {
                arr[i] = scan.nextInt();
            }

            Arrays.sort(arr);

//            int max = 0;
//            for(int i = 1; i < arr.length; i++) {
//                int value = arr[i] - arr[i - 1];
//                if(max < value) {
//                    max = value;
//                }
//            }
//            System.out.println(max);
            System.out.println(arr[N - 1] - arr[0]);
        }
    }
}
