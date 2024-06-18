package kr.goldenmine.baekjoon.platinum.platinum1.p16133;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if(ch == ' ') continue;

            if(getPriorityOfOperator(ch) != -1) { // 연산자면
                if(buffer.length() > 0) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                }
                result.add(String.valueOf(ch));
            } else {
                buffer.append(ch);
            }
//            System.out.println(buffer);
        }


        if(buffer.length() > 0) {
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
        while (!stack.isEmpty()){
            String popped = stack.pop();
            // 결과를 담는 스택에 저장
            result.add(popped);
        }

        return result;
    }

    public static int trunc(double num) {
        if(num >= 0) {
            return (int) Math.floor(num);
        } else {
            return (int) Math.ceil(num);
        }
    }

    public static BigInteger calculate(List<String> values) {
        Stack<BigInteger> stack = new Stack<>();

        for(String s : values) {
            int priority = getPriorityOfOperator(s.charAt(0));

            if(priority != -1) {
                switch(s.charAt(0)) {
                    case '+': // 이항 연산
                    case '-':
                    case '*':
                    case '/':
                    case '^': {
                        BigInteger op2 = stack.pop();

//                        if(stack.isEmpty()) {
////                            System.out.println(op2);
//                            if(s.charAt(0) == '-')
//                                stack.push(String.valueOf(-Long.parseLong(op2)));
//                            continue;
//                        }
                        BigInteger op1 = stack.pop();

                        if (s.charAt(0) == '+') stack.push(op1.add(op2));
                        else if (s.charAt(0) == '-')
                            stack.push(op1.subtract(op2));
                        else if (s.charAt(0) == '*')
                            stack.push(op1.multiply(op2));
                        else if (s.charAt(0) == '/')
                            stack.push(op1.divide(op2));
                        else if (s.charAt(0) == '^') {
//                            System.out.println(op1 + "^" + op2);
                            stack.push(power(op1, op2));
                        }
                    }
                        break;
                    case '#': // 단항 연산
                        BigInteger op1 = stack.pop();
//                        System.out.println(Math.sqrt(Long.parseLong(op1)));
//                        stack.push(floorSqrt(op1));

                        stack.push(op1.sqrt());

//                        stack.push(String.valueOf(res));
//                        stack.push((long) Math.sqrt(op1));
                        break;
                }
            } else {
                stack.push(new BigInteger(s));
            }
        }

        return stack.pop();
    }

    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }

    static BigInteger two = new BigInteger("2");
    static BigInteger one = new BigInteger("1");
    static BigInteger zero = new BigInteger("0");

    public static BigInteger power(BigInteger x, BigInteger y) {
        if(y.compareTo(two) > 0) {
            BigInteger res = power(x, y.divide(two));
            if(y.mod(two).compareTo(zero) == 0) {
                return res.multiply(res);
            } else {
                return res.multiply(res).multiply(x);
            }
        } else if(y.compareTo(two) == 0) {
            return x.multiply(x);
        } else if(y.compareTo(one) == 0){
            return x;
        } else {
            return one;
        }
    }

    public static long floorSqrt(final long x) {
        if ((x & 0xfff0000000000000L) == 0L) return (long) StrictMath.sqrt(x);
        final long result = (long) StrictMath.sqrt(2L*(x >>> 1));
        return result*result - x > 0L ? (long) result - 1 : (long) result;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String text = scan.next();
        text = text.endsWith("=") ? text.substring(0, text.length() - 1) : text;
        List<String> split = splitInfix(text);
//        System.out.println(split);
        List<String> postfix = infixToPostfix(split);
//        System.out.println(postfix);

        System.out.println(calculate(postfix));
    }

    // #(#(2+3)+4)
    // 2^(3+#2)^2
    // 2*(2+1)^#(8+#2)^#(2+2)*2 = 2*3^3^2*2
}
