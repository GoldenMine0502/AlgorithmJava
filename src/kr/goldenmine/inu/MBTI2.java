package kr.goldenmine.inu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class MBTI2 {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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

    // 두 MBTI 간의 점수(다른 글자의 개수)를 계산하는 메서드
    private static int getMbtiScore(String mbti1, String mbti2) {
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
INFP
ESFP
INTJ
     */

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        // 여러 테스트 케이스를 처리하기 위한 루프
        int N = scan.nextInt();
        // 남성 MBTI 입력 및 개수 계산
        Map<String, Integer> menCounts = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String mbti = scan.nextString();
            menCounts.put(mbti, menCounts.getOrDefault(mbti, 0) + 1);
        }

        // 여성 MBTI 입력 및 개수 계산
        Map<String, Integer> womenCounts = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String mbti = scan.nextString();
            womenCounts.put(mbti, womenCounts.getOrDefault(mbti, 0) + 1);
        }

        // 전체 MBTI 유형 집합 생성
        Set<String> allMbtiTypes = new HashSet<>();
        allMbtiTypes.addAll(menCounts.keySet());
        allMbtiTypes.addAll(womenCounts.keySet());

        long totalScore = 0;

        // 점수가 높은 순서대로 (4점 -> 1점) 그리디 매칭
        /*
        MBTI 기준이 아닌 점수를 기준으로 먼저 매칭
         */
        for (int score = 4; score >= 1; score--) {
            for (String m_mbti : new String[] { "INFP", "ENTP" }) {
                if (menCounts.getOrDefault(m_mbti, 0) == 0) continue;

                for (String f_mbti : new String[] { "ISTJ", "ESTP" }) {
                    if (womenCounts.getOrDefault(f_mbti, 0) == 0) continue;

                    if (getMbtiScore(m_mbti, f_mbti) == score) {
                        // 매칭 가능한 쌍의 수 계산
                        int matches = Math.min(menCounts.getOrDefault(m_mbti, 0), womenCounts.getOrDefault(f_mbti, 0));

                        if (matches > 0) {
                            // 총점에 더하기
                            totalScore += (long) matches * score;

                            // 매칭된 인원수만큼 제외
                            menCounts.put(m_mbti, menCounts.get(m_mbti) - matches);
                            womenCounts.put(f_mbti, womenCounts.get(f_mbti) - matches);
                        }
                    }
                }
            }
        }

        System.out.println(totalScore);
    }
}

/*
1. 반대 가설 설정
우리의 그리디 알고리즘으로 찾은 해(Solution)를 GRD라고 합시다. 그리고, GRD가 최적해가 아니라고 가정해 보겠습니다.

이 가정이 맞다면, GRD보다 더 높은 총점을 가진 최적해 OPT (Optimal Solution)가 반드시 존재해야 합니다. 즉, 총점(OPT) > 총점(GRD) 입니다.

2. 첫 번째 차이점 분석
GRD와 OPT는 서로 다른 해이므로, 두 해가 만들어낸 점수별 매칭 개수에 차이가 있을 것입니다.

n_s(S) : 해 S에서 점수가 s점인 매칭의 개수

점수가 가장 높은 4점부터 1점까지 순서대로 비교했을 때, GRD와 OPT의 매칭 개수가 처음으로 달라지는 점수를 k라고 합시다.

모든 s > k에 대해, n_s(GRD) = n_s(OPT) 입니다. (즉, 4점, 5점이 있다면 그 매칭 수는 동일)

k점에서 n_k(GRD) ≠ n_k(OPT) 입니다.

그리디 알고리즘의 정의에 따라, GRD는 k점보다 높은 점수의 매칭을 마친 후 남은 사람들 중에서 k점짜리 매칭을 만들 수 있는 최대 개수를 만듭니다. OPT 역시 동일한 사람들 중에서 매칭을 수행하므로, k점짜리 매칭을 GRD보다 더 많이 만들 수는 없습니다.

따라서, n_k(GRD) ≥ n_k(OPT) 입니다.
그런데 우리는 k점에서 두 해가 다르다고 가정했으므로, 결론은 n_k(GRD) > n_k(OPT)가 됩니다.

3. 모순 도출
이제 총점(OPT)과 총점(GRD)의 차이를 분석해 봅시다.

총점(OPT) - 총점(GRD) = Σ s * n_s(OPT) - Σ s * n_s(GRD)

이 식을 k점을 기준으로 세 부분으로 나눌 수 있습니다.

k점보다 높은 점수(s > k)의 합:
이 부분은 두 해의 매칭 수가 동일하므로 0입니다.

k점 점수의 합:
k * n_k(OPT) - k * n_k(GRD) = k * (n_k(OPT) - n_k(GRD))
n_k(GRD) > n_k(OPT) 이므로 이 값은 음수입니다.

k점보다 낮은 점수(s < k)의 합:
GRD는 OPT보다 k점짜리 매칭에 더 많은 사람을 사용했습니다. 그 차이는 Δn = n_k(GRD) - n_k(OPT) 쌍, 즉 2 * Δn 명입니다.
따라서 OPT는 GRD보다 2 * Δn 명의 사람을 k점 미만의 점수를 내는 매칭에 더 사용했습니다. 이 사람들이 OPT에서 만들 수 있는 최대 점수는 모든 쌍이 k-1점을 기록하는 경우입니다.
따라서 k점 미만 점수의 차이는 아무리 커도 (k-1) * Δn 을 넘을 수 없습니다.
Σ_{s<k} s * n_s(OPT) - Σ_{s<k} s * n_s(GRD) ≤ (k-1) * Δn

이 세 부분을 종합하면 다음과 같은 부등식이 성립합니다.

총점(OPT) - 총점(GRD) ≤ 0 + k * (-Δn) + (k-1) * Δn
총점(OPT) - 총점(GRD) ≤ -k*Δn + k*Δn - Δn
총점(OPT) - 총점(GRD) ≤ -Δn

Δn은 0보다 큰 정수이므로, -Δn은 반드시 0보다 작은 값입니다.
이는 총점(OPT) - 총점(GRD) < 0, 즉 총점(OPT) < 총점(GRD) 라는 결론으로 이어집니다.

이는 우리가 최초에 설정한 가정, 총점(OPT) > 총점(GRD)에 정면으로 위배됩니다. 이로써 논리적 모순이 발생했습니다.

결론
최초의 가정("그리디 해가 최적해가 아니다")이 모순을 일으켰으므로, 이 가정은 거짓입니다. 따라서 점수 기준 그리디 알고리즘이 찾아낸 해는 항상 최적해임이 증명되었습니다.
 */