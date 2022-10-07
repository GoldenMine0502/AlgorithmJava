package kr.goldenmine.programmers.course30.p1_118666;

public class Solution {
    public String solution(String[] survey, int[] choices) {
        int scoreR = 0; // R
        int scoreT = 0; // T
        int scoreC = 0; // C
        int scoreF = 0; // F
        int scoreJ = 0; // J
        int scoreM = 0; // M
        int scoreA = 0; // A
        int scoreN = 0; // N

        for(int i = 0; i < survey.length; i++) {
            String key = survey[i];
            int score = choices[i];

            switch (key) {
                case "RT": scoreR += score - 4; break;
                case "TR": scoreT += score - 4; break;
                case "CF": scoreC += score - 4; break;
                case "FC": scoreF += score - 4; break;
                case "JM": scoreJ += score - 4; break;
                case "MJ": scoreM += score - 4; break;
                case "AN": scoreA += score - 4; break;
                case "NA": scoreN += score - 4; break;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(scoreR < scoreT ? "T" : "R");
        sb.append(scoreC < scoreF ? "F" : "C");
        sb.append(scoreJ < scoreM ? "M" : "J");
        sb.append(scoreA < scoreN ? "N" : "A");

        System.out.println(sb);

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
