package kr.goldenmine.baekjoon.bronze.bronze2.p30700;

public class Main {
    public static void main(String[] args) throws Exception {
        byte[] chars = {'K', 'O', 'R', 'E', 'A'};

        byte[] bytes = new byte[1001];
        int size = System.in.read(bytes) - 1;

        int count = 0;
        for(int i = 0; i < size; i++) {
            if(bytes[i] == chars[count % 5]) {
                count++;
            }
        }

        System.out.println(count);
    }
}
