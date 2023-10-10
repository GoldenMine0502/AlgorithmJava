package kr.goldenmine.silver.silver1.p1747S;

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
        while(true) {
            if(isPel(N) && isPrime(N)) {
                System.out.println(N);
                break;
            }
            N++;
        }
//        System.out.println(isPrime(N));
    }

    public static boolean isPrime(int n) {
        if(n == 1) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for(int i = 3; i * i <= n; i += 2) {
            if(n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPel(int n) {
        String str = String.valueOf(n);

        for(int i = 0; i < str.length(); i++) {
            int rev = str.length() - 1 - i;
            if(str.charAt(i) != str.charAt(rev)) return false;
        }
        return true;
    }
}
