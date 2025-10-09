package kr.goldenmine.baekjoon.bronze.bronze4.p10987;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[101];
        int N = System.in.read(bytes, 0, 101);
        int c = 0;
        for(int i = 0; i < N; i++) {
            if(bytes[i] == 'a' || bytes[i] == 'e' || bytes[i] == 'i' || bytes[i] == 'o' || bytes[i] == 'u') c++;
        }
        System.out.println(c);
    }
}
