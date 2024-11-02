//package kr.goldenmine.contest.c984_div3.B_hack;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("test.txt");
//        if(!file.exists()) {
//            file.createNewFile();
//        }
//        int M = 33000;
//        BufferedWriter w = new BufferedWriter(new FileWriter(file));
//        w.write("1\n");
//        w.write(200000 + " " + M + "\n");
//        for(int i = 1; i <= M; i++) {
//            w.write(i + " 1\n");
//        }
//        w.close();

        int M = 200000;
        System.out.println(1);
        System.out.println("200000 " + M);
        Random r = new Random();
        r.setSeed(1234);
        for (int i = 1; i <= M; i++) {
            System.out.println(i + " " + (r.nextInt(1000) + 1));
        }
    }
}
