package kr.goldenmine.baekjoon.platinum.platinum4.p3111F_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String A = scan.next();
        String T = scan.next();

        ArrayDeque<Character> left = new ArrayDeque<>();
        ArrayDeque<Character> right = new ArrayDeque<>();

        int leftIndex = 0;
        int rightIndex = T.length() - 1;

        boolean toRight = true;

        while(leftIndex <= rightIndex) {
            if(toRight) {
                char ch = T.charAt(leftIndex);

                System.out.println("left " + ch + ", " + leftIndex + ", " + rightIndex);
                // 검열 체크
                if(leftIndex + A.length() < T.length()) {
                    boolean check = true;

                    for(int i = 0; i < A.length(); i++) {
                        if(A.charAt(i) != T.charAt(i + leftIndex)) {
                            check = false;
                            break;
                        }
                    }

                    if(check) {
                        System.out.println("censored");
                        leftIndex += A.length();
                        toRight = false;
                        continue;
                    }
                }

                left.addLast(ch);

                leftIndex++;
            } else {
                char ch = T.charAt(rightIndex);

                System.out.println("right " + ch + ", " + leftIndex + ", " + rightIndex);
                // 검열 체크
                if(rightIndex - A.length() + 1 >= 0) {
                    boolean check = true;

                    // ne
                    // abneneab
                    // 01234567
                    // rightIndex = 5
                    // T처음 = 5 - 2 + 0
                    for(int i = 0; i < A.length(); i++) {
                        System.out.println("check " + (rightIndex - A.length() + 1 + i));
                        if(A.charAt(i) != T.charAt(rightIndex - A.length() + 1 + i)) {
                            check = false;
                            break;
                        }
                    }

                    if(check) {
                        rightIndex -= A.length() + 1;
                        System.out.println("censored");
                        toRight = true;
                        continue;
                    }
                }

                right.addLast(ch);

                rightIndex--;
            }
        }

        System.out.println(Arrays.toString(left.toArray()));
        System.out.println(Arrays.toString(right.toArray()));

        while(!right.isEmpty()) {
            left.addLast(right.removeLast());
        }

        StringBuilder sb = new StringBuilder();
        for(char ch : left) {
            sb.append(ch);
        }

        System.out.println(sb);
    }
}
