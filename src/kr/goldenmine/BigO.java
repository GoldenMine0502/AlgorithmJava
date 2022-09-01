package kr.goldenmine;

public class BigO {
    public static void main(String[] args) {
        System.out.println(isEven(1000000000000000L));
//        System.out.println(isEven2(1000000000));
    }

    //
    // O(1)
    // O(N^2) -> 버블정렬, 칵테일정렬, ...
    // O(N log N) -> 퀵정렬, 머지정렬, 힙정렬,
    // O(??) -> radix, ...
    // O(N!) -> bogo

    public static void bigO() { // O(N^2 + NM) = O(N(N + M)) -> 100만 얼마
        int N = 1000; // -> N = 100000 => O(N^2) = 100억? -> 100초 -> 시간초과
        // O(2^N) -> N 10, 20 -> 시간초과
        int M = 100;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

            }

            for(int j = 0;j < M; j++) {

            }
        }
    }

    // 양수 기준
    public static boolean isEven(long i) {
        while(i >= 2) {
            i -= 2;
        }

        if(i == 0) return true;
        return false;
    }

    public static boolean isEven2(long i) {
        return i % 2 == 0;
    }
}
