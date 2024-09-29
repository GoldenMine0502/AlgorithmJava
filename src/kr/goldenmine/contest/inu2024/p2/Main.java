package kr.goldenmine.contest.inu2024.p2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int X = scan.nextInt();
        int Y = scan.nextInt();

        int X2 = scan.nextInt();
        int Y2 = scan.nextInt();

        // 세 기물이 한 직선 위에 있는 경우 (사이에 있는 경우) 넘어야함
        if((X == X2 && X2 == 0 && Y2 < Y) || (Y == Y2 && Y2 == 0 && X2 < X)) {
            System.out.println(3);
        } else { // 한 직선 위에 있지 않은 경우
            if(X == 0 || Y == 0) { // X축 또는 Y축을 이동할 필요가 없는 경우
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }
}
