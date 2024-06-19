package kr.goldenmine.others.programmers.test_3_2.p1;

import java.util.*;

public class Solution {
    static class Point {
        int x;
        int value;
        boolean evicted;
        boolean zerostate;

        Point(int x, int value, boolean evicted, boolean zerostate) {
            this.x = x;
            this.value = value;
            this.evicted = evicted;
            this.zerostate = zerostate;
        }
    }

    // 첫 값을 떼는 경우
    public static int dp1(int[] sticker) {
        int[][] dp = new int[2][sticker.length + 1];

        dp[0][1] = 0;
        dp[1][1] = sticker[0];

        int max = sticker[0];
        for(int i = 2; i <= sticker.length; i++) {
            // 지금 값을 떼지 않는 경우
            // 뗀 경우나 안 뗀 경우나 최댓값 적용
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);

            // 지금 값을 떼는 경우
            // 이전 값을 안떼면 이전 값 + 현재 스티커, 이전 값을 떼면 전전값 + 현재 스티커
            if(i < sticker.length) {
                dp[1][i] = Math.max(dp[0][i - 1] + sticker[i - 1], dp[1][i - 2] + sticker[i - 1]);
            } else { // 0번째를 뗀 상태라서 마지막이면 고려불가능
                //ignore
            }
            max = Math.max(max, dp[0][i]);
            max = Math.max(max, dp[1][i]);
        }

//        System.out.println(Arrays.toString(dp[0]));
//        System.out.println(Arrays.toString(dp[1]));

        return max;
    }


    // 첫 값을 안 떼는 경우
    public static int dp2(int[] sticker) {
        int[][] dp = new int[2][sticker.length + 1];

        dp[0][1] = 0;
        dp[1][1] = 0;

        int max = 0;
        for(int i = 2; i <= sticker.length; i++) {
            // 지금 값을 떼지 않는 경우
            // 뗀 경우나 안 뗀 경우나 최댓값 적용
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);

            // 지금 값을 떼는 경우
            // 이전 값을 안떼면 이전 값 + 현재 스티커, 이전 값을 떼면 전전값 + 현재 스티커
            dp[1][i] = Math.max(dp[0][i - 1] + sticker[i - 1], dp[1][i - 2] + sticker[i - 1]);

            max = Math.max(max, dp[0][i]);
            max = Math.max(max, dp[1][i]);
        }

//        System.out.println(Arrays.toString(dp[0]));
//        System.out.println(Arrays.toString(dp[1]));

        return max;
    }

    public int solution(int[] sticker) {
        return Math.max(dp1(sticker), dp2(sticker));
    }

    public static void main(String[] args) {
//        int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
        int[] sticker = {1};

        System.out.println(new Solution().solution(sticker));
    }
}
