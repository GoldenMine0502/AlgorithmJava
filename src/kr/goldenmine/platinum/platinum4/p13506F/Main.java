package kr.goldenmine.platinum.platinum4.p13506F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

    public static int[] getFailure(String key) {
        int[] pi = new int[key.length()];
        pi[0] = 0;

        int j = 0;

        for(int i = 1; i < key.length(); i++) {
            while(j > 0 && key.charAt(i) != key.charAt(j))
                j = pi[j - 1];

            if(key.charAt(i) == key.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static List<Integer> kmp(String text, String key) {
        List<Integer> indices = new ArrayList<>();

        int[] pi = getFailure(key);

        int j = 0;

        for(int i = 0; i < text.length(); i++) {
            while(j > 0 && text.charAt(i) != key.charAt(j))
                j = pi[j - 1];

            if(text.charAt(i) == key.charAt(j)) {
                if(j == key.length() - 1) {
                    indices.add(i - key.length() + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        return indices;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String str = scan.next();
        int[] fail = getFailure(str);

        int max = fail[fail.length - 1];

        int zeroCount = 0;
        for(int i = 0; i < fail.length; i++) {
            if(fail[i] != 0) break;
            zeroCount++;
        }

        int firstLen = Math.min(zeroCount, max);
        int maxMiddle = 0;
        for(int i = fail.length - 1 - firstLen; i >= 0; i--) {

            maxMiddle = Math.max(maxMiddle, fail[i]);
        }

//        System.out.println(Arrays.toString(fail) + ", " + firstLen + ", " + maxMiddle + ", " + max);

        int totalLen = Math.min(max, maxMiddle);
        if(totalLen > 0 && max >= maxMiddle) {
            String result = str.substring(0, totalLen);

            if(str.endsWith(result)) {
                System.out.println(str.substring(0, totalLen));
            } else {
                System.out.println(-1);
            }
        } else {
            System.out.println(-1);
        }
    }
}
