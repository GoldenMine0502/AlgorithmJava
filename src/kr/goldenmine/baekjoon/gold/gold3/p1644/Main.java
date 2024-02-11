package kr.goldenmine.baekjoon.gold.gold3.p1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
//        long time = System.currentTimeMillis();

        List<Integer> primes = new ArrayList<>();
        // 190ms
        for(int i = 2; i <= 4100000; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }

//        System.out.println(System.currentTimeMillis() - time);

        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int left = 0;
        int right = 0;
        int count = 0;
        int total = 0;

        while(left <= N && right <= N) {
            if(total < N) {
//                if(primes.get(right) >= N) break;
                total += primes.get(right++);
            }

            if(total > N) {
                total -= primes.get(left++);
                if(primes.get(left) > N) break;
            }

            if(total == N) {
                count++;
                total -= primes.get(left++);
                if(primes.get(left) > N) break;
            }
//            System.out.println(count + ", " + total + ", " + left + ", " + right);
        }

        System.out.println(count);
    }

    static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
}
