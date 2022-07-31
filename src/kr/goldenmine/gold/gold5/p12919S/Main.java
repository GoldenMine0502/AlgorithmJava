package kr.goldenmine.gold.gold5.p12919S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // 찾아보니 12919번은 다 T -> S로 하라 하던데
    // 이건 S -> T 풀이입니다
    // bCount가 홀수이면 문자열이 뒤집혔다는 것을 의미합니다.
    public static int find(String text, String result, int bCount) {
        String compareText = (bCount % 2 == 0 ? text : getReversedString(text));

        // 핵심은 백트래킹답게 애초에 답이 아닐거 같은 가지는 쳐내는것
        // 아래 코드를 통해 기존 O(2^N)에서 O(N^2)으로 팍줄어버립니다. O(N^2) -> 확인할 문자열 길이 N * contain을 통한 문자열 비교 N
        if(!result.contains(compareText)) return 0;

        if(result.equals(compareText)) return 1;
        if(text.length() >= result.length()) return 0;

        // bCount == 1이면 뒤집힌 상태.
        String textA = text + "A";
        String textB = getReversedString(text + "B");

        int resultA = find(textA, result, bCount);
        if(resultA >= 1) return resultA;

        int resultB = find(textB, result, bCount + 1);
        if(resultB >= 1) return resultB;

        return resultA + resultB;
    }

    // 뒤집은 문자열 구하기
    // abcd -> dcba
    public static String getReversedString(String text) {
        StringBuilder sb = new StringBuilder(text);
        sb.reverse();

        return sb.toString();
    }

    // 텍스트 내 B의 갯수 세기
    public static int getBCount(String text) {
        int count = 0;

        for(int i = 0; i < text.length(); i++) {
            count += text.charAt(i) == 'B' ? 1 : 0;
        }

        return count;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String a = scan.nextLine();
        String b = scan.nextLine();

        // diff가 홀수면 결과가 뒤집힌 상태이고 짝수면 결과가 뒤집히지 않은 상태.
        int diff = getBCount(b) - getBCount(a);
        int result = find(a, diff % 2 == 0 ? b : getReversedString(b), 0);

        System.out.println(result);
    }
}
