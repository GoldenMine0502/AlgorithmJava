package kr.goldenmine.baekjoon.gold.gold5.p1662S;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    static class Data {
        String text;
        int multiplier;
        int lastLength;

        public Data(String text, int multiplier) {
            this.text = text;
//            this.lastLength += text.length();
            this.multiplier = multiplier;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = scan.next();

        ArrayDeque<Data> stack = new ArrayDeque<>();
        StringBuilder buffer = new StringBuilder();

        int totalLength = 0;

        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if(ch == '(') {
                // 마지막 문자열 가져오기
                int multiplier = buffer.charAt(buffer.length() - 1) - '0';
                buffer.setLength(buffer.length() - 1);


                stack.push(new Data(buffer.toString(), multiplier));

//                System.out.println("push " + buffer + ", " + multiplier);
                buffer.setLength(0);
            } else if(ch == ')') { // 간단하게 3(5)
                Data data = stack.pop();
                String in = buffer.toString();
                buffer.setLength(0);


                int length = (data.lastLength + in.length()) * data.multiplier + data.text.length();

//                System.out.println("pop " + data.lastLength + ", " + data.multiplier + ", " + in.length() + ", " + length);

                if(stack.size() > 0) {
                    Data data2 = stack.pop();
                    data2.lastLength += length;
                    stack.push(data2);
                } else {
                    totalLength += length;
                }
            } else {
                buffer.append(ch);
            }
        }

        if(buffer.length() > 0) {
            totalLength += buffer.length();
        }

        System.out.println(totalLength);
    }
}
