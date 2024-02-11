package kr.goldenmine.baekjoon.platinum.platinum1.p5615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;
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

    static Random random = new Random();

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        // 2S + 1 = 2xy + x + y + 1 = (2x + 1)(2y + 1)
        // 합성수의 경우 두개로 쪼갤 수 있으니 2S + 1이 소수인지 보면 된다.
        // 이때 밀러 라빈 소수 판정법을 이용함

        int sum = 0;
        int k = 10;

        for(int i = 0; i < N; i++) {
            long input = scan.nextInt();

            if(isPrime(input + 1, k)) {
                sum++;
            }
        }

        System.out.println(sum);
//        System.out.println(BigInteger.probablePrime(100, random));
    }


    static int randomRange(int start, int finish) {
        return random.nextInt(finish - start + 1) + start;
    }

    public static boolean isPrime(long n, int iteration)
    {
        /** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;

        long s = n - 1;
        while (s % 2 == 0)
            s /= 2;

        Random rand = new Random();
        for (int i = 0; i < iteration; i++)
        {
            long r = Math.abs(rand.nextLong());
            long a = r % (n - 1) + 1, temp = s;
            long mod = modPow(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n - 1)
            {
                mod = mulMod(mod, mod, n);
                temp *= 2;
            }
            if (mod != n - 1 && temp % 2 == 0)
                return false;
        }
        return true;
    }
    /** Function to calculate (a ^ b) % c **/
    public static long modPow(long a, long b, long c)
    {
        long res = 1;
        for (int i = 0; i < b; i++)
        {
            res *= a;
            res %= c;
        }
        return res % c;
    }
    /** Function to calculate (a * b) % c **/
    public static long mulMod(long a, long b, long mod)
    {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }

    // Utility function to do modular
    // exponentiation. It returns (x^y) % p
//    static int power(int x, int y, int p) {
//
//        int res = 1; // Initialize result
//
//        //Update x if it is more than or
//        // equal to p
//        x = x % p;
//
//        while (y > 0) {
//
//            // If y is odd, multiply x with result
//            if ((y & 1) == 1)
//                res = (res * x) % p;
//
//            // y must be even now
//            y = y >> 1; // y = y/2
//            x = (x * x) % p;
//        }
//
//        return res;
//    }
//
//    // This function is called for all k trials.
//    // It returns false if n is composite and
//    // returns false if n is probably prime.
//    // d is an odd number such that d*2<sup>r</sup>
//    // = n-1 for some r >= 1
//    static boolean miillerTest(int d, int n) {
//
//        // Pick a random number in [2..n-2]
//        // Corner cases make sure that n > 4
//        int a = 2 + (int)(Math.random() % (n - 4));
//
//        // Compute a^d % n
//        int x = power(a, d, n);
//
//        if (x == 1 || x == n - 1)
//            return true;
//
//        // Keep squaring x while one of the
//        // following doesn't happen
//        // (i) d does not reach n-1
//        // (ii) (x^2) % n is not 1
//        // (iii) (x^2) % n is not n-1
//        while (d != n - 1) {
//            x = (x * x) % n;
//            d *= 2;
//
//            if (x == 1)
//                return false;
//            if (x == n - 1)
//                return true;
//        }
//
//        // Return composite
//        return false;
//    }
//
//    // It returns false if n is composite
//    // and returns true if n is probably
//    // prime. k is an input parameter that
//    // determines accuracy level. Higher
//    // value of k indicates more accuracy.
//    static boolean isPrime(int n, int k) {
//
//        // Corner cases
//        if (n <= 1 || n == 4)
//            return false;
//        if (n <= 3)
//            return true;
//
//        // Find r such that n = 2^d * r + 1
//        // for some r >= 1
//        int d = n - 1;
//
//        while (d % 2 == 0)
//            d /= 2;
//
//        // Iterate given number of 'k' times
//        for (int i = 0; i < k; i++)
//            if (!miillerTest(d, n))
//                return false;
//
//        return true;
//    }

//    public static boolean isPrime(int n) {
//        int s = 0;
//
//        if(n == 2) return true;
//        if(n < 2 || n % 2 == 0) return false;
//
//        int t = n - 1;
//
//        while(t % 2 == 0) {
//            s++;
//            t >>= 1;
//        }
//
//        for(int i = 0; i < 25; i++) {
//            int b = randomRange(2, n - 1);
//
//            if(millerRabinTest(n, b, s, t)) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public static boolean millerRabinTest(int n, int b, int s, int t) {
//        long x = pow(b, t, n);
//
//        if(x == 1 || x == n - 1) {
//            return true;
//        } else {
//            for(int i = 0; i < s; i++) {
//                if(x == n - 1) {
//                    return true;
//                }
//
//                x = pow(x, 2, n);
//            }
//
//            return false;
//        }
//    }
//
//    public static long pow(long value, long exponent, long mod) {
//        if(exponent == 0) {
//            return 1;
//        }
//        if(exponent == 1) {
//            return value % mod;
//        }
//        if(exponent == 2) {
//            return (value * value) % mod;
//        }
//
//        long half = pow(value, exponent / 2, mod) % mod;
//
//        if(exponent % 2 == 0) {
//            return (half * half) % mod;
//        } else {
//            return (((half * half) % mod) * (value % mod)) % mod;
//        }
//    }
}
