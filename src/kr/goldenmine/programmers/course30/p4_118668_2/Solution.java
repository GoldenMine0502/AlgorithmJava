package kr.goldenmine.programmers.course30.p4_118668_2;

public class Solution {

    // problems의 원소는 [alp_req, cop_req, alp_rwd, cop_rwd, cost]의 형태로 이루어져 있습니다.
    public int solution(int alp, int cop, int[][] problems) {
        /*
        선택지:
        1. 알고 공부 (알고 + 1)
        2. 코딩 공부 (코딩 + 1)
        3. 문제 풀이 (알고 + n, 코딩 + m, cost)

        DP..?
         */
        int maxAlp = alp;
        int maxCop = cop;

        for (int i = 0; i < problems.length; i++) {
            int problemAlp = problems[i][0];
            int problemCop = problems[i][1];

            maxAlp = Math.max(maxAlp, problemAlp);
            maxCop = Math.max(maxCop, problemCop);
        }

        // results[5][10] -> alp 5, cop 10까지 가는 최소 코스트
        int[][] costs = new int[maxAlp + 1][maxCop + 1];

        for (int ap = 0; ap <= maxAlp; ap++) {
            for (int cp = 0; cp <= maxCop; cp++) {
                costs[ap][cp] = 1000000000;
            }
        }

        costs[alp][cop] = 0;

        // 문제를 풀어서 오버하는 경우를 생각 안 함 ...
        for (int ap = alp; ap <= maxAlp; ap++) {
            for (int cp = cop; cp <= maxCop; cp++) {
                // 코딩력 1을 증가함
                if (cp > 0 && cp < maxCop)
                    costs[ap][cp + 1] = Math.min(costs[ap][cp + 1], costs[ap][cp] + 1);

                // 알고력 1을 증가함
                if (ap > 0 && ap < maxAlp)
                    costs[ap + 1][cp] = Math.min(costs[ap + 1][cp], costs[ap][cp] + 1);

                // 문제를 풀어 증가함
                for (int i = 0; i < problems.length; i++) {
                    int problemAlp = problems[i][0];
                    int problemCop = problems[i][1];
                    int problemAlpIncrement = problems[i][2];
                    int problemCopIncrement = problems[i][3];
                    int problemCost = problems[i][4];

                    if (ap >= problemAlp && cp >= problemCop) { // 일단 조건은 충족해야지
                        int nextAlp = Math.min(ap + problemAlpIncrement, maxAlp);
                        int nextCop = Math.min(cp + problemCopIncrement, maxCop);
                        costs[nextAlp][nextCop] = Math.min(costs[nextAlp][nextCop], costs[ap][cp] + problemCost);
                    }
                }
            }
        }

        return costs[maxAlp][maxCop];
    }

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};

        System.out.println(new Solution().solution(alp, cop, problems));
    }
}
