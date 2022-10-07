package kr.goldenmine.programmers.course30.p4_118668;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static class Node {
        int alp;
        int cop;
        int cost;

        public Node(int alp, int cop, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.cost = cost;
        }
    }

    // problems의 원소는 [alp_req, cop_req, alp_rwd, cop_rwd, cost]의 형태로 이루어져 있습니다.
    public int solution(int alp, int cop, int[][] problems) {
        /*
        선택지:
        1. 알고 공부 (알고 + 1)
        2. 코딩 공부 (코딩 + 1)
        3. 문제 풀이 (알고 + n, 코딩 + m, cost)

        DP..?
         */
        int maxAlp = 0;
        int maxCop = 0;

        int MAX_ARR_SIZE = 200;

        for(int i = 0; i < problems.length; i++) {
            int problemAlp = problems[i][0];
            int problemCop = problems[i][1];

            maxAlp = Math.max(maxAlp, problemAlp);
            maxCop = Math.max(maxCop, problemCop);
        }

        // results[5][10] -> alp 5, cop 10까지 가는 최소 코스트
        int[][] costs = new int[MAX_ARR_SIZE + 1][MAX_ARR_SIZE + 1];

        for(int ap = 0; ap <= MAX_ARR_SIZE; ap++) {
            for (int cp = 0; cp <= MAX_ARR_SIZE; cp++) {
                costs[ap][cp] = 1000000000;
            }
        }

        costs[alp][cop] = 0;

        // 문제를 풀어서 오버하는 경우를 생각 안 함 ...
        for(int ap = alp; ap <= MAX_ARR_SIZE; ap++) {
            for(int cp = cop; cp <= MAX_ARR_SIZE; cp++) {
                // 코딩력 1을 증가함
                if(cp > 0)
                    costs[ap][cp] = Math.min(costs[ap][cp], costs[ap][cp - 1] + 1);

                // 알고력 1을 증가함
                if(ap > 0)
                    costs[ap][cp] = Math.min(costs[ap][cp], costs[ap - 1][cp] + 1);

                // 문제를 풀어 증가함
                for(int i = 0; i < problems.length; i++) {
                    int problemAlp = problems[i][0];
                    int problemCop = problems[i][1];
                    int problemAlpIncrement = problems[i][2];
                    int problemCopIncrement = problems[i][3];
                    int problemCost = problems[i][4];

                    if(ap - problemAlpIncrement >= problemAlp && cp - problemCopIncrement >= problemCop) { // 일단 조건은 충족해야지
                        if(ap - problemAlpIncrement >= 0 && cp - problemCopIncrement >= 0) { // 코딩력, 알고력이 음수일 수는 없다.
                            costs[ap][cp] = Math.min(costs[ap][cp], costs[ap - problemAlpIncrement][cp - problemCopIncrement] + problemCost);
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for(int ap = maxAlp; ap <= MAX_ARR_SIZE; ap++) {
            for (int cp = maxCop; cp <= MAX_ARR_SIZE; cp++) {
                System.out.print(costs[ap][cp] + " ");
                min = Math.min(min, costs[ap][cp]);
            }
            System.out.println();
        }

        return min;
    }

    public static void main(String[] args) {

    }
}
