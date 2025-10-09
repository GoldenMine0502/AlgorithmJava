package kr.goldenmine.inu;

import java.util.HashMap;
import java.util.Map;

public class MBTI {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    // 두 MBTI 문자열을 비교하여 점수를 계산하는 함수.
    public static int calculateScore(String mbti1, String mbti2) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti2.charAt(i)) {
                score++;
            }
        }
        return score;
    }
    /*
2
ESTJ
INTP
ISFP
ENTP

2
ISFP
ESTJ
INTJ
ESFP
     */

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        /*
        풀이 키워드: #Greedy
        티어: Silver I
        어떤 남자를 먼저 선택하든, 그 남자가 얻을 수 있는 최대 점수를 얻는 선택은
        다른 남자가 최대 점수를 얻을 가능성에 영향을 주지 않습니다.
         */
        int N = scan.nextInt();

        Map<String, Integer> menMbtiCount = new HashMap<>();
        Map<String, Integer> womenMbtiCount = new HashMap<>();

        // 남자 mbti
        for(int i = 0; i < N; i++) {
            String mbti = scan.nextString();
            menMbtiCount.put(mbti, menMbtiCount.getOrDefault(mbti, 0) + 1);
        }
        // 여자 mbti
        for(int i = 0; i < N; i++) {
            String mbti = scan.nextString();
            womenMbtiCount.put(mbti, womenMbtiCount.getOrDefault(mbti, 0) + 1);
        }

        long totalScore = 0;

        // 2. 그리디 알고리즘을 사용한 매칭
        // 모든 남성 MBTI 유형에 대해 반복
        for (String manMbti : menMbtiCount.keySet()) {
            int menCount = menMbtiCount.get(manMbti);

            // 해당 MBTI를 가진 모든 남성에 대해 최고의 짝을 찾아 매칭
            for (int i = 0; i < menCount; i++) {
                String bestMatchWomanMbti = "";
                int maxScore = -1; // 점수는 0점 이상이므로 -1로 초기화

                // 매칭 가능한 여성들 중 가장 높은 점수를 주는 상대를 찾는다.
                for (String womanMbti : new String[] { "INTJ", "ESFP"}) {
                    // 해당 MBTI를 가진 여성이 남아있는 경우에만 고려
                    if (womenMbtiCount.get(womanMbti) > 0) {
                        int currentScore = calculateScore(manMbti, womanMbti);

                        // 현재 점수가 기록된 최고 점수보다 높으면 갱신
                        if (currentScore > maxScore) {
                            maxScore = currentScore;
                            bestMatchWomanMbti = womanMbti;
                        }

                        // 점수는 최대 4점이므로, 4점 상대를 찾으면 더 탐색할 필요 없음 (최적화)
                        if (maxScore == 4) {
                            break;
                        }
                    }
                }

                // 최고의 매칭 대상을 찾았다면 점수를 합산하고, 매칭된 여성 카운트를 1 감소
                if (maxScore != -1) {
                    totalScore += maxScore;
                    womenMbtiCount.put(bestMatchWomanMbti, womenMbtiCount.get(bestMatchWomanMbti) - 1);
                }
            }
        }

        System.out.println(totalScore);
    }
}
