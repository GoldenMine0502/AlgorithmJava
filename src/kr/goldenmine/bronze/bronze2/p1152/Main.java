package kr.goldenmine.bronze.bronze2.p1152;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = scan.nextLine();

        String[] split = text.split(" ");
        int words = 0;
        for(int i = 0; i < split.length; i++) {
            if(split[i].length() > 0) {
                words++;
            }
        }
        System.out.println(words);
//        int tempLen = 0;
//        int words = 0;
//
//        for(int i = 0; i < text.length(); i++) {
//            char ch = text.charAt(i); // text[i]
//
//            if(ch == ' ') {
//                if(tempLen > 0) {
//                    words++;
//                    tempLen = 0;
//                }
//            } else {
//                tempLen++;
//            }
//        }
//
//        if(tempLen > 0)
//            words++;

//        System.out.println(words);
    }
}
