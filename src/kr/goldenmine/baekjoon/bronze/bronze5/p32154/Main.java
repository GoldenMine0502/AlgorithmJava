package kr.goldenmine.baekjoon.bronze.bronze5.p32154;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] list = {
                "A B C D E F G H J L M",
                "A C E F G H I L M",
                "A C E F G H I L M",
                "A B C E F G H L M",
                "A C E F G H L M",
                "A C E F G H L M",
                "A C E F G H L M",
                "A C E F G H L M",
                "A C E F G H L M",
                "A B C F G H L M"
        };
        Scanner scan = new Scanner(System.in);

        String res = list[scan.nextInt() - 1];
        System.out.println(res.split(" ").length);
        System.out.println(res);
    }
}
