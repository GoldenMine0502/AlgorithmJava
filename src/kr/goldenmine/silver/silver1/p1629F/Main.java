package kr.goldenmine.silver.silver1.p1629F;

import java.util.Scanner;

// https://st-lab.tistory.com/237
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

        // 11같은거면 -> A^8, A^2, A^1로 쪼개서 곱하기
        // 이때 참조하는 cache 인덱스는 3, 1, 0
        long[] cache = new long[32]; // 2^0 -> 0번째 인덱스 결과값

        cache[0] = 1L; // A^(2^0)
        cache[1] = 2L; // A^(2^1)

        // A^(2^2)부터, A^(2^4), A^(2^8), ...
        for(int i = 2; i < cache.length; i++) {
            long value = (cache[i - 1] * cache[i - 1]) % C;

            cache[i] = value;

            System.out.println("2^" + (int)Math.pow(2, i) + ": " + cache[i]);
        }

        long result = 1;

        while(B > 1) {
            // 크지 않은 거듭제곱 구하기
            int index = 0;
            while(index < 32 && Math.pow(2, index + 1) < B) {
                index++;
            }

            System.out.println("start " + B + ", " + result + ", " + cache[index]);
            result *= cache[index];
            result = result % C;
            B -= Math.pow(2, index);
            System.out.println("finish " + B + ", " + result);
            Thread.sleep(1000L);
        }

        System.out.println(result);
    }
}
