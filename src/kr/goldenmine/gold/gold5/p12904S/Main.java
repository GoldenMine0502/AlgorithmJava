package kr.goldenmine.gold.gold5.p12904S;

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

    public static int find(String text, String result, int bCount) {
//        System.out.println(text + ", " + bCount + ", " + (bCount % 2 == 0 ? text : getReversedString(text)) + ", " + result);
        String compareText = (bCount % 2 == 0 ? text : getReversedString(text));

        if(!result.contains(compareText)) {
//            System.out.println("not contain");
            return 0;
        }
        if(result.equals(compareText)) return 1;
        if(text.length() >= result.length()) return 0;

        // bCount == 1이면 뒤집힌 상태.
        String textA = text + "A";
        String textB = getReversedString(text) + "B";

        int resultA = find(textA, result, bCount);
        if(resultA >= 1) return resultA;

        int resultB = find(textB, result, bCount + 1);
        if(resultB >= 1) return resultB;

        return resultA + resultB;
    }

    public static String getReversedString(String text) {
        StringBuilder sb = new StringBuilder(text);
        sb.reverse();

        return sb.toString();
    }

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

        int diff = getBCount(b) - getBCount(a);
        int result = find(a, diff % 2 == 0 ? b : getReversedString(b), 0);
//        int result = find(a, b, 0);
//        int result = find(a, b, 0, (getBCount(b) - getBCount(a) + 2) % 2);
//        System.out.println(alreadyChecked.size());

        System.out.println(result);
    }
}
