package kr.goldenmine.baekjoon.bronze.bronze5.p27889;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = (new Scanner(System.in)).next();

        // NLCS: North London Collegiate School
        //BHA: Branksome Hall Asia
        //KIS: Korea International School
        //SJA: St. Johnsbury Academy

        if(str.equals("NLCS")) {
            System.out.println("North London Collegiate School");
        } else if(str.equals("BHA")) {
            System.out.println("Branksome Hall Asia");
        } else if(str.equals("KIS")) {
            System.out.println("Korea International School");
        } else if(str.equals("SJA")) {
            System.out.println("St. Johnsbury Academy");
        }
    }
}
