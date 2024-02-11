package kr.goldenmine.others.exams.finals.ch5;

import java.util.Arrays;
import java.util.List;

public class DPCoinChange {
    public static void main(String[] args) {
        /*
        // 각 코인별 개수를 저장할 2차원 배열, 전부 0으로 초기화
        types[N + 1][d.length + 1]

        for i = 1 to n C[i] = INF
        C[0] = 0
        for j = 1 to n
            minimumCoinIndex = -1;

            for i = 1 to k
                coinAmount = d_i
                if(coinAmount <= j)
                    if(C[j - d_i] + 1 < C[j])
                        C[j] = dp[j - d_i] + 1;
                        minimumCoinIndex = i;

            if(minimumCoinIndex >= 0)
                types[i][minimumCoinIndex] = types[i - d_minimumCoinIndex)][minimumCoinIndex] + 1;

                for kk = 1 to k
                    if(kk != minimumCoinIndex)
                        if(i >= d_i)
                            types[i][k] = types[i - d_minimumCoinIndex][k];

        // types[N][코인인덱스]가 곧 해당하는 액면가에 대한 코인의 갯수가 된다.
         */
        List<Integer> coins = Arrays.asList(16, 10, 5, 1);

        int N = 20;
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = N + 1;

            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(dp));

        int result = dp[N] > N ? -1 : dp[N];
        System.out.println(result);
    }
}
