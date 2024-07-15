package kr.goldenmine.baekjoon.gold.gold5.p2011S;

public class Main {
    public static void main(String[] args) throws Exception {
        byte[] buffer = new byte[5001];
        int N = System.in.read(buffer) - 1;

        final int MOD = 1000000;
        int[] dp = new int[N];

        for(int i = 0; i < N; i++) {
            char current = (char) buffer[i];
            char last = i >= 1 ? (char) buffer[i - 1] : 0;

            // 한글자 가능
            if('1' <= current && current <= '9') {
                dp[i] += i >= 1 ? dp[i - 1] : 1;
                dp[i] %= MOD;
            }
            // 두글자 가능
            if('1' <= last && last <= '2') {
                if(last == '1' || '0' <= current && current <= '6'){
                    dp[i] += i >= 2 ? dp[i - 2] : 1;
                    dp[i] %= MOD;
                }
            }
        }
        System.out.println(dp[N - 1]);
    }
}
