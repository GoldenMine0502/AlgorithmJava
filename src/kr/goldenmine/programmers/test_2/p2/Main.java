package kr.goldenmine.programmers.test_2.p2;

import java.util.Arrays;

public class Main {
    public int[] solution(int brown, int yellow) {
        // 사이드 4개 제거 -> 6

        // 2*2=4
        // 2*3=6  사이즈 하나 커질때마다 brown 2 증가
        // 4*3=10

        // (2 + n) * (2 + m) -> (4 + 2 * (n + m))
        // yellow는 (n - 1) * (m - 1)개 있음
        // 최대 크기 2500 * 2500 = 6250000

        int resultX = 0;
        int resultY = 0;

        for(int n = 0; n <= 2500; n++) {
            for(int m = 0; m <= 2500; m++) {
                int sizeX = 2 + n;
                int sizeY = 2 + m;

                int currentBrown = 4 + 2 * (n + m);
                int currentYellow = (n) * (m);

                if(currentBrown == brown && currentYellow == yellow) {
                    resultX = sizeX;
                    resultY = sizeY;
                    break;
                }
            }
        }

        return new int[] { Math.max(resultX, resultY), Math.min(resultX, resultY) };
    }

    public static void main(String[] args) {
        int brown = 24;
        int yellow = 24;

        System.out.println(Arrays.toString(new Main().solution(brown, yellow)));
    }
}
