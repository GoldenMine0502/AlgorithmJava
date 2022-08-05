package kr.goldenmine.platinum.platinum4.p3111F_2;

import java.io.*;
import java.util.ArrayDeque;
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

    static class CharacterData {
        char ch;
        int equalsSize;

        public CharacterData(char ch, int equalsSize) {
            this.ch = ch;
            this.equalsSize = equalsSize;
        }

        @Override
        public String toString() {
            return String.valueOf(ch);
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        String A = scan.next();
        String T = scan.next();

        ArrayDeque<Character> left = new ArrayDeque<>(T.length());
        ArrayDeque<Character> right = new ArrayDeque<>(T.length());

        int leftIndex = 0;
        int rightIndex = T.length() - 1;

        boolean pointerDirectionRight = true;

        int equalsSize = 0;

        // 스택에 추가시키는 부분
        while (leftIndex <= rightIndex) {
            if (pointerDirectionRight) { // 오른쪽으로 이동
                char ch = T.charAt(leftIndex);
                left.addLast(ch);
                if (ch == A.charAt(equalsSize)) {
                    equalsSize++;

                    // 뱉어내기
                    if (equalsSize == A.length()) {
                        for (int i = 0; i < A.length(); i++) {
                            left.removeLast();
                        }

                        pointerDirectionRight = false;
                        equalsSize = 0;
                    }
                } else { // 처음 equalsSize가 증가했던 부분 까지 뒤로 돌아감
                    while (equalsSize > 0) {
                        leftIndex--;
                        equalsSize--;
                        left.removeLast();
                    }
                }
//                System.out.println("left " + ch + " " + equalsSize + " " + leftIndex);
                leftIndex++;
            } else { // 왼쪽으로 이동
                char ch = T.charAt(rightIndex);
                right.addLast(ch);
                if (ch == A.charAt(A.length() - 1 - equalsSize)) {
                    equalsSize++;

                    // 뱉어내기
                    if (equalsSize == A.length()) {
                        for (int i = 0; i < A.length(); i++) {
                            right.removeLast();
                        }

                        pointerDirectionRight = true;
                        equalsSize = 0;
                    }
                } else { // 처음 equalsSize가 증가했던 부분 까지 뒤로 돌아감
                    while (equalsSize > 0) {
                        rightIndex++;
                        equalsSize--;
                        right.removeLast();
                    }
                }
//                System.out.println("right " + ch + " " + equalsSize + " " + rightIndex);
                rightIndex--;
            }
        }

//        System.out.println(Arrays.toString(left.toArray()));
//        System.out.println(Arrays.toString(right.toArray()));

        // 스택에서 빼는 부분
        while (true) {
            if (pointerDirectionRight) {
                if (right.isEmpty()) break;

                char ch = right.removeLast();
                left.addLast(ch);
                if (ch == A.charAt(equalsSize)) {
                    equalsSize++;

                    // 뱉어내기
                    if (equalsSize == A.length()) {
                        for (int i = 0; i < A.length(); i++) {
                            left.removeLast();
                        }

                        pointerDirectionRight = false;
                        equalsSize = 0;
                    }
                } else {
                    while (equalsSize > 0) {
                        leftIndex--;
                        equalsSize--;
                        right.addLast(left.removeLast());
                    }
                }
//                System.out.println("2left " + ch + " " + equalsSize);
                leftIndex++;
            } else {
                if (left.isEmpty()) break;

                char ch = left.removeLast();
                right.addLast(ch);
                if (ch == A.charAt(A.length() - 1 - equalsSize)) {
                    equalsSize++;

                    // 뱉어내기
                    if (equalsSize == A.length()) {
                        for (int i = 0; i < A.length(); i++) {
                            right.removeLast();
                        }

                        pointerDirectionRight = true;
                        equalsSize = 0;
                    }
                } else {
                    while (equalsSize > 0) {
                        rightIndex++;
                        equalsSize--;
                        left.addLast(right.removeLast());
                    }
                }
//                System.out.println("2right " + ch + " " + equalsSize + " " + rightIndex);
                rightIndex--;
            }
        }

//        System.out.println(Arrays.toString(left.toArray()));
//        System.out.println(Arrays.toString(right.toArray()));

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        while (!right.isEmpty()) {
            left.addLast(right.removeLast());
//            System.out.println(left.peek());
        }

        for (char ch : left) {
            output.write(String.valueOf(ch));
        }

        output.flush();

        // 결과 출력
//        while (!right.isEmpty()) {
//            left.addLast(right.removeLast());
//            System.out.println(left.peek());
//        }

//        for (char ch : left) {
//            System.out.print(ch);
//        }
//        System.out.println();
//        for (char ch : right) {
//            System.out.print(ch);
//        }
    }
}
