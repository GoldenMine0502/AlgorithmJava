package kr.goldenmine.baekjoon.gold.gold1.p9813S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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
        else if (operator == '^') return 3; // 2^3*2
        else if (operator == '#') return 4; // 2^3*2

        return -1;
    }

    public static List<String> splitInfix(String input) {
        StringBuilder buffer = new StringBuilder();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == ' ') continue;

            if (getPriorityOfOperator(ch) != -1) { // 연산자면
                if (buffer.length() > 0) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                }
                result.add(String.valueOf(ch));
            } else {
                buffer.append(ch);
            }
//            System.out.println(buffer);
        }


        if (buffer.length() > 0) {
            result.add(buffer.toString());
        }

        return result;
    }

    static List<String> infixToPostfix(List<String> text) {
        Stack<String> stack = new Stack<>();
        List<String> result = new Stack<>();

        for (int i = 0; i < text.size(); i++) { // 식 전체 순회하기
            String data = text.get(i);

            int priority = getPriorityOfOperator(data.charAt(0));
            if (priority == -1) { // 피연산자
                result.add(data);
            } else { // 연산자
                switch (data.charAt(0)) {
                    case '(':
                    case '#':
                        stack.push(data);
                        break;
                    case ')':
                        while (!stack.isEmpty()) {
                            String popped = stack.pop();

                            if (popped.equals("(")) {
                                break;
                            }

                            result.add(popped);
                        }

                        break;
                    case '^': // 얘는 스택에 있어야하므로 나보다 우선순위 높은 애만 빼주기
                        while (!stack.isEmpty() && getPriorityOfOperator(data.charAt(0)) < getPriorityOfOperator(stack.peek().charAt(0))) {
                            String popped = stack.pop();

                            result.add(popped);
                        }
                        stack.push(data);
                        break;

                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!stack.isEmpty() && getPriorityOfOperator(data.charAt(0)) <= getPriorityOfOperator(stack.peek().charAt(0))) {
                            String popped = stack.pop();

                            result.add(popped);
                        }
                        stack.push(data);
                        break;
                }

            }
        }

        // 스택에 남은 값들 다 결과에 옮기기
        while (!stack.isEmpty()) {
            String popped = stack.pop();
            // 결과를 담는 스택에 저장
            result.add(popped);
        }

        return result;
    }

    public static int trunc(double num) {
        if (num >= 0) {
            return (int) Math.floor(num);
        } else {
            return (int) Math.ceil(num);
        }
    }

    public static double calculate(List<String> values) {
        Stack<Double> stack = new Stack<>();

        for (String s : values) {
            int priority = getPriorityOfOperator(s.charAt(0));

            if (priority != -1) {
                switch (s.charAt(0)) {
                    case '+': // 이항 연산
                    case '-':
                    case '*':
                    case '/':
                    case '^': {
                        double op2 = stack.pop();

//                        if(stack.isEmpty()) {
////                            System.out.println(op2);
//                            if(s.charAt(0) == '-')
//                                stack.push(String.valueOf(-Long.parseLong(op2)));
//                            continue;
//                        }
                        double op1 = stack.pop();

                        if (s.charAt(0) == '+') stack.push(op1 + op2);
                        else if (s.charAt(0) == '-')
                            stack.push(op1 - op2);
                        else if (s.charAt(0) == '*')
                            stack.push(op1 * op2);
                        else if (s.charAt(0) == '/')
                            stack.push(op1 / op2);
                    }
                    break;
                }
            } else {
                stack.push(Double.parseDouble(s));
            }
        }

        return stack.pop();
    }

    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for (; ; ) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }

    private static final String[] characters = new String[]{"+", "-", "*", "/"};
//
//    static int[] verifyN = new int[4];
    static boolean verify = false;
    static double EPSILON = 1e-12;

    public static void check(int[] n, List<String> list, int answer) {
        List<String> postfix = infixToPostfix(list);
        double res = calculate(postfix);
        if (Math.abs(res - answer) < EPSILON) {
//        System.out.println(list + ", " + res + ", " + answer + ", " + (res == answer));
//            System.out.println("?");
//            System.arraycopy(n, 0, verifyN, 0, 4);
            verify = true;
        }
    }

    // 50 17 15 16 9
    // 50 / (17 - 15) - 16

    // 30 4 2 5 10
    // 30 / (4 + 2) + 5

    // 1 2 7 4 11
    // (1 + 2 * 7) - 4

    // 1 42 7 2 2
    // 42 / (1 + 2) * 7

    // 1 42 7 2 9
    // 1 + 42 / 7 + 2

    // 2 5 11 2 91
    // (2 + 5) * (11 + 2)

    // 2 5 11 2 93
    // NO!

    // 7 3 2 15 30
    // (7 - (3 + 2)) * 15

    // 7 3 2 15 29
    // (3 + 15) * 2 - 7

    // 2 17 11 21 84
    // 21 * (17 - (11 + 2))

    // 2 17 11 21 83
    // (17 * 11 - 21) / 2

    // 2 13 2 13 11
    // ((13 + 13) / 2) - 2
    // 2 13 2 13 14
    // (2 + 13 + 13) / 2
    // 2 13 2 13 17
    // (2 + 13) * 2 - 13
    // 2 13 2 13 21
    // No

    public static void values(int[] n, int[] arr, int answer, boolean[] visited, int index) {
        if(verify) return;

        if (index == 4) {
            operator(arr, answer, new boolean[4], new int[]{-1, -1, -1}, 0);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(verify) return;
            if (!visited[i]) {
                visited[i] = true;
                arr[index] = n[i];
                values(n, arr, answer, visited, index + 1);
                arr[index] = -1;
                visited[i] = false;
            }
        }
    }

    public static void operator(int[] n, int answer, boolean[] visited, int[] arr, int index) {
        if (verify) return;

        if (index == 3) {
            String[] list = {
                    String.valueOf(n[0]),
                    characters[arr[0]],
                    String.valueOf(n[1]),
                    characters[arr[1]],
                    String.valueOf(n[2]),
                    characters[arr[2]],
                    String.valueOf(n[3])
            };

            // 괄호 치는 경우의 수 = (1, 2), (2, 3), (3, 4), (1, 2, 3), (2, 3, 4)
            // 96 11 11 2 2
            // 11 * ((7 - 2) + 3) = 40
            List<String> list0 = Arrays.asList(list);
            List<String> list1 = Arrays.asList("(", list[0], list[1], list[2], ")", list[3], list[4], list[5], list[6]);
            List<String> list12 = Arrays.asList("(", list[0], list[1], list[2], ")", list[3], "(", list[4], list[5], list[6], ")");
            List<String> list2 = Arrays.asList(list[0], list[1], list[2], list[3], "(", list[4], list[5], list[6], ")");
            List<String> list3 = Arrays.asList(list[0], list[1], "(", list[2], list[3], list[4], ")", list[5], list[6]);

            List<String> list4 = Arrays.asList("(", list[0], list[1], list[2], list[3], list[4], ")", list[5], list[6]);
            List<String> list5 = Arrays.asList(list[0], list[1], "(", list[2], list[3], list[4], list[5], list[6], ")");

            List<String> list41 = Arrays.asList("(", "(", list[0], list[1], list[2], ")", list[3], list[4], ")", list[5], list[6]);
            List<String> list43 = Arrays.asList("(", list[0], list[1], "(", list[2], list[3], list[4], ")", ")", list[5], list[6]);
            List<String> list53 = Arrays.asList(list[0], list[1], "(", "(", list[2], list[3], list[4], ")", list[5], list[6], ")");
            List<String> list52 = Arrays.asList(list[0], list[1], "(", list[2], list[3], "(", list[4], list[5], list[6],  ")", ")");

            for (List<String> i : Arrays.asList(list0, list1, list12, list2, list3, list4, list5, list41, list52, list43, list53)) {
                try {
                    check(arr, i, answer);
                    if(verify) return;
                } catch (Exception ex) { /*ignore*/ }
            }
            return;
        }

        // 연산자는 무제한으로 올 수 있음
        for (int i = 0; i < characters.length; i++) {
            arr[index] = i;
            operator(n, answer, visited, arr, index + 1);
            arr[index] = -1;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        StringBuilder sb = new StringBuilder();
        while (true) {
            int n1 = scan.nextInt();
            if (n1 == -1) break;
            int n2 = scan.nextInt();
            int n3 = scan.nextInt();
            int n4 = scan.nextInt();
            int n5 = scan.nextInt();

            int[] n = {n1, n2, n3, n4};

            verify = false;
            values(n, new int[4], n5, new boolean[4], 0);

            sb.append(n1).append(' ').append(n2).append(' ').append(n3).append(' ').append(n4).append(' ').append(n5).append(' ').append(verify ? "OK!" : "NO!").append('\n');
//            if(verify) {
//                System.out.println(verifyN[0] + " " + verifyN[1] + " " + verifyN[2] + " " + verifyN[3] + " " + verifyN[4] + " " + "OK!");
//            } else {
//
//            }
        }
        System.out.println(sb);
    }
}
