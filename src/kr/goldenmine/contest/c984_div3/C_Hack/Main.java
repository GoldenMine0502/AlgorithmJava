//package kr.goldenmine.contest.c984_div3.C_Hack;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();

        for(int i = 0; i < 50000; i++) {
            text.append("1100");
        }
        System.out.println(1);
        System.out.println(text);
        System.out.println(200000);
        Random r = new Random();
        r.setSeed(1234);
        for(int i = 0; i < 200000; i++) {
            System.out.println((r.nextInt(200000) + 1) + " " + r.nextInt(2));
        }
    }
}
