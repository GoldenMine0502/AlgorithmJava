package kr.goldenmine;

public class TimeComplexityTest {
    public static void main(String[] args) {
        final int SIZE = 1_000_000_000;

        long time;

        // 단순 1억회 반복
        time = System.currentTimeMillis();
        for(int i = 0; i < SIZE; i++) {

        }
        System.out.println(System.currentTimeMillis() - time);


        time = System.currentTimeMillis();
        for(int i = 0; i < SIZE; i++) {
            if(i == 1) {

            } else {

            }
        }
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();

        int[] arr = new int[1000];
        int[] arr2 = new int[1000];

        for(int i = 0; i < SIZE; i++) {
            if(arr[0] < arr2[0]) {

            }
        }
        System.out.println(System.currentTimeMillis() - time);
    }
}
