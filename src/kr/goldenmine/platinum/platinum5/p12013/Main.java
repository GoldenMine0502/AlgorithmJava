package kr.goldenmine.platinum.platinum5.p12013;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int[] arr = new int[N + 1];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        // D[i][j]를 num[i~j]로 만들 수 있는 수입니다.
        // 이 때, unique한 수로 만들 수 없으면 0으로 둡니다.
        // 예를 들어 1 1 1 1일 경우 D[1][2]는 2, D[1][3]은 0이 됩니다.
        // 이 때 D[i][i] = num[i]로 두고,
        // D[i][j]는 sep = i to j-1에 대해 D[i][sep] == D[sep+1][j]일 경우 D[i][j] = max(D[i][j], D[i][sep]+1)입니다.
        //
        // 이 때 D 테이블의 최댓값이 정답입니다.

        /*
4
1
1
1
2
         */
        int ans = 0;
        int[][] dp = new int[N + 1][N + 1];
        for(int i = 0; i < N; i++) {
            dp[i][i] = arr[i];
            ans = Math.max(ans, arr[i]);
        }

        for(int size = 1; size < N; size++) {
            for(int i = 0; i < N - size; i++) {
                for(int sep = i; sep < i + size; sep++) {
//                    System.out.println("size=" + size + ", i=" + i + ", sep=" + sep + ": (" + (sep + 1) + ", " + (i + size) + ")");
                    if(dp[i][sep] == dp[sep + 1][i + size] && dp[i][sep] != 0) {
                        dp[i][i + size] = Math.max(dp[i][i + size], dp[i][sep] + 1);
//                        System.out.println("update: (" + i + ", " + (i + size) +  ") to " + dp[i][i + size]);

//                        for(int t1 = 0; t1 < N; t1++) {
//                            for(int t2 = 0; t2 < N; t2++) {
//                                System.out.print(dp[t1][t2] + " ");
//                            }
//                            System.out.println();
//                        }
                    }
                    ans = Math.max(ans, dp[i][i + size]);
                }
            }
        }
        System.out.println(ans);
//        for(int i = 1; i <= N; i++) {
//            for(int j = 1; j <= i; j++) {
//
//            }
//        }
    }
}
