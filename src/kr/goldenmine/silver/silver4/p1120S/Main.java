package kr.goldenmine.silver.silver4.p1120S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String a = scan.next();
        String b = scan.next();

        // 무조건 a가 길도록 만들고 싶음
        if (b.length() > a.length()) {
            // 스왑
            String temp = a;
            a = b;
            b = temp;


        }

        int min = Integer.MAX_VALUE;

        // 이제 b가 어느 인덱스에서 가장 차이가 작은지 계산해보자.
        for (int bFirstIndex = 0; bFirstIndex < a.length() - b.length() + 1; bFirstIndex++) {
            int diff = 0;

            for (int bIndex = 0; bIndex < b.length(); bIndex++) {
                int aIndex = bIndex + bFirstIndex;
                char aCh = a.charAt(aIndex);
                char bCh = b.charAt(bIndex);

                if (aCh != bCh) {
                    diff++;
                }
            }

            min = Math.min(min, diff);
        }

        System.out.println(min);
    }
}
