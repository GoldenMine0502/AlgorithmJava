package kr.goldenmine.baekjoon.bronze.bronze2.p17201;

public class Main {

    public static void main(String[] args) throws Exception {
        byte[] b = new byte[12];
        System.in.read(b, 0, 12);
        int N = 2 * (b[0] - '0') + 1;
        for (int i = 2; i < N; i++) {
            if(b[i] == b[i + 1]) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}
