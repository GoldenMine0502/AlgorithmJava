package kr.goldenmine.baekjoon.silver.silver3.p11478;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = new Scanner(System.in).next();

        HashSet<String> subs = new HashSet<String>();
        for(int l = 1; l <= str.length(); l++) {
            for(int s = 0; s <= str.length() - l; s++) {
                String sub = str.substring(s, s + l);

                subs.add(sub);
//                System.out.println(sub);
            }
        }
        System.out.println(subs.size());
    }
}
