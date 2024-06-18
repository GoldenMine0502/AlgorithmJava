package kr.goldenmine.baekjoon.gold.gold2.p1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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


    // 연산자에 따른 각각 다른 우선순위를 가진다.
    static int getPriorityOfOperator(char operator) {
        if (operator == '(' || operator == ')') return 0;
        else if (operator == '+' || operator == '-') return 1;
        else if (operator == '*' || operator == '/') return 2;
        else if (operator == '#' || operator == '^') return 3; // 2^3*2

        return -1;
    }

    public static List<String> splitInfix(String input) {
        StringBuilder buffer = new StringBuilder();
        List<String> result = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if(getPriorityOfOperator(ch) != -1) { // 연산자면
                if(buffer.length() > 0) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                    result.add(String.valueOf(ch));
                }
            } else {
                buffer.append(ch);
            }
        }

        if(buffer.length() > 0) {
            result.add(buffer.toString());
        }

        return result;
    }

    static List<Character> infixToPostfix(String text) {
        int len = 0;

        Stack<Character> stack = new Stack<>();
        List<Character> result = new Stack<>();

        for (int i = 0; i < text.length(); i++) { // 식 전체 순회하기
            char data = text.charAt(i);

            int priority = getPriorityOfOperator(data);
            if (priority == -1) { // 피연산자
                result.add(data);
            } else { // 연산자
                switch (data) {
                    case '(':
                        stack.push(data);
                        break;
                    case ')':
                        while (!stack.isEmpty()) {
                            char popped = stack.pop();

                            if (popped == '(') {
                                break;
                            }

                            result.add(popped);
                        }

                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!stack.isEmpty() && getPriorityOfOperator(data) <= getPriorityOfOperator(stack.peek())) {
                            char popped = stack.pop();

                            result.add(popped);
                        }
                        stack.push(data);
                        break;
                }

            }
        }

        // 스택에 남은 값들 다 결과에 옮기기
        while (!stack.isEmpty()){
            char popped = stack.pop();
            // 결과를 담는 스택에 저장
            result.add(popped);
        }

        return result;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();
        String infix = scan.next();

        StringBuilder sb = new StringBuilder();
        for(char ch : infixToPostfix(infix)) {
            sb.append(ch);
        }
        System.out.println(sb);
    }
}
