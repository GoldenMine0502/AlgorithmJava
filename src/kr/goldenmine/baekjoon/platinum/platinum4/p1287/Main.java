package kr.goldenmine.baekjoon.platinum.platinum4.p1287;

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
//                if(ch == '0' && buffer.length() == 0) throw new RuntimeException("C");
                buffer.append(ch);
            }
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

//            if(i == 0 && data.charAt(0) == '0') throw new RuntimeException("start 0");

            int priority = getPriorityOfOperator(data.charAt(0));
            if (priority == -1) { // 피연산자
                result.add(data);
                stack.push("%");
            } else { // 연산자
                switch (data.charAt(0)) {
                    case '(':
                        if(i > 0 && getPriorityOfOperator(text.get(i - 1).charAt(0)) == -1) throw new RuntimeException("연산자 두개");
                        stack.push(data);
                        break;
                    case ')':
                        if(i < text.size() - 1 && getPriorityOfOperator(text.get(i + 1).charAt(0)) == -1) throw new RuntimeException("연산자 두개2");
                        boolean find = false;
                        boolean added = false;
                        while (!stack.isEmpty()) {
                            String popped = stack.pop();

                            if(popped.equals("%")) {
                                added = true;
                                continue;
                            }

                            if (popped.equals("(")) {
                                find = true;
                                break;
                            }

                            if(!added) {
                                added = true;
//                                stack.push("%");
                            }
                            result.add(popped);

                        }
                        if(!find) throw new RuntimeException("D");
                        if(!added) throw new RuntimeException("E");
                        stack.push("%");
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!stack.isEmpty() && (stack.peek().charAt(0) == '%' || getPriorityOfOperator(data.charAt(0)) <= getPriorityOfOperator(stack.peek().charAt(0)))) {
                            String popped = stack.pop();

                            if(popped.equals("%")) {
                                continue;
                            }

                            if(popped.equals("(")) {
                                throw new RuntimeException("A");
                            }

                            result.add(popped);
                            stack.push("%");
                        }
                        stack.push(data);
//                        stack.push("%");
                        break;
                }

            }
//            System.out.println(stack + ", " + result);
        }

        // 스택에 남은 값들 다 결과에 옮기기
        while (!stack.isEmpty()){
            String popped = stack.pop();

            if(popped.equals("%")) {
                continue;
            }
            if(popped.equals("(")) {
                throw new RuntimeException("B");
            }
            // 결과를 담는 스택에 저장
            result.add(popped);
        }

        return result;
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
                    case '/':{
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
                        else if (s.charAt(0) == '/') {
//                            if(op1.compareTo(zero) == 0) throw new RuntimeException("zero");
                            stack.push(op1.divide(op2));
                        }
                    }
                    break;
                }
            } else {
                stack.push(new BigInteger(s));
            }
        }

        if(stack.size() > 1) {
            throw new RuntimeException();
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String text = scan.next();

//        System.out.println(split);
        try {
            List<String> split = splitInfix(text);
            List<String> postfix = infixToPostfix(split);
//            System.out.println("postfix: " + postfix);
            System.out.println(calculate(postfix).toString());
        } catch(Exception ex) {
//            ex.printStackTrace();
            System.out.println("ROCK");
        }
    }
}
