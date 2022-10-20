package kr.goldenmine.exams.digits;

import java.util.Scanner;

public class Digits2To16 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.next();

        String[] digits = {
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
        };

        String result = "";

        int start = input.length() % 4 - 1;
        if(start < 0) start += 4;

        for(int i = start; i < input.length(); i += 4) {
            int temp = (i >= 3 ? input.charAt(i - 3) - '0' : 0) << 3
                    | (i >= 2 ? input.charAt(i - 2) - '0' : 0) << 2
                    | (i >= 1 ? input.charAt(i - 1) - '0' : 0) << 1
                    | (i >= 0 ? input.charAt(i - 0) - '0': 0) << 0;

//            System.out.println(i + ", " + temp);

            result += digits[temp];
        }

        System.out.println("0x" + result);
    }
}
