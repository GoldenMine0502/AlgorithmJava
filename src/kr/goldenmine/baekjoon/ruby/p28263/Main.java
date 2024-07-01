package kr.goldenmine.baekjoon.ruby.p28263;

public class Main {
    // 최대 1억까지 구해야함
    static int[] primes = new int[1_0000_0001];
    public static void main(String[] args) {

    }

    public static boolean check(int n) {
        for(int i = 1; i < n; i++) {
            if(gcd(n, i) == 1) { // 최대공약수가 1이면 서로소
                if(pow(n, i - 1, i) != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int gcd(int n, int k) {
        while(k != 0) {
            int mod = n % k;
            n = k;
            k = mod;
        }
        return n;
    }

    public static int pow(int a, int b, int MOD) {
        if(b > 2) {
            int half = pow(a , b/ 2, MOD) % MOD;
            if(b % 2 == 0) {
                return (half * half) % MOD;
            } else {
                return (((half * half) % MOD) * a) % MOD;
            }
        } else if(b == 2) {
            return (a * a) % MOD;
        } else if(b == 1) {
            return a % MOD;
        } else {
            return 1;
        }
    }
}
