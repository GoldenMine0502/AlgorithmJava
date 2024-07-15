package kr.goldenmine.baekjoon.bronze.bronze2.p1159S;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] b = new byte[4654];
        int s = System.in.read(b);
        int[] count = new int[26];

        boolean next = false;
        for(int i = 0; i < s; i++) {
            if(next) {
                count[b[i] - 'a']++;
                next = false;
            }
            if(b[i] == '\n') next = true;
        }

        byte[] r = new byte[26];
        int c = 0;
        for(int i = 0; i < 26; i++) {
            if(count[i] >= 5) {
                r[c++] = (byte) (i + 'a');
            }
        }
        if(c > 0) {
            System.out.println(new String(r, 0, c));
        } else {
            System.out.println("PREDAJA");
        }
    }
}
