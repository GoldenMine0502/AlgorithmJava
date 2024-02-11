package kr.goldenmine.baekjoon.gold.gold1.p1311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] dp;
    static final int INF = 16_000_000;	// MAX : 11,000,000
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        // i : 현재 위치한 도시, j : 지금까지 방문한 도시 2진수
        dp = new int[n][(1 << n) - 1];	// ex) 1 << 5 = 100000(2) = 32 -> 우리의 최대값 : 11111(2) 이므로 1 빼기

        // 1) Map 입력받아 대입하기
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int weight = Integer.parseInt(st.nextToken());
                map[i][j] = weight;
            }
        }

        // dp배열 초기화
//        for(int i = 0; i < n; i++) {
//            Arrays.fill(dp[i], INF);
//        }

        System.out.println(dfs(0, 0));
    }

    // 어느 도시에서 시작하든지 최소비용은 같기 때문에 편안하게 0번도시부터 시작하자
    // @param: city - 현재 위치한 도시 인덱스, visitBitMask - 지금까지 방문한 도시 2진수
    // DFS 알고리즘
    private static int dfs(int now, int visitBitmask) {

        if(visitBitmask == (1 << n) - 1) {	// 모든 도시를 방문했다면
            return 0;	// 현재 도시 -> 0번쨰(시작) 도시 이동 거리
        }

        if(dp[now][visitBitmask] != 0) {	// dp값이 존재하는경우
            return dp[now][visitBitmask];
        }

        int result = INF;

        for(int i = 0; i < n; i++) {	// 현재 도시(city)에서 각 i의 도시로 이동한 경우에 대해 DFS 수행
            if((visitBitmask & (1 << i)) == 0) {
                result = Math.min(result, map[now][i] + dfs(now + 1, visitBitmask | (1 << i)));
//                System.out.println(city + ", " + visitBitmask + ", " + dp[city][visitBitmask]);
            }
        }

        return dp[now][visitBitmask] = result;
    }
}
