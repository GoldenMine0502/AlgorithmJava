package kr.goldenmine.baekjoon.gold.gold4.p2258;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Meat {
        int weight;
        int price;

        Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Meat{" +
                    "weight=" + weight +
                    ", price=" + price +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        long M = scan.nextInt();

        List<Meat> meats = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            meats.add(new Meat(scan.nextInt(), scan.nextInt()));
        }

        meats.sort((o1, o2) -> {
            int c = Integer.compare(o1.weight, o2.weight);
            if(c == 0) {
                return Integer.compare(o1.price, o2.price);
            }
            return c;
        });
        /*
5 9
1 2
2 4
3 6
4 10
4 8
         */

//        System.out.println(meats);

        int weightSum = 0;
        int lastWeightSum = 0;
        int currentWeight = 0;
        int currentPrice = 0;
        int currentWeightSum = 0;
        long min = Long.MAX_VALUE;
        for(int i = 0; i < meats.size(); i++) {
            Meat meat = meats.get(i);
            if(meat.weight > currentWeight) {
                lastWeightSum = currentWeightSum;

                currentWeight = meat.weight;
                currentWeightSum = currentWeight;


                weightSum += meat.weight;
                currentPrice = meat.price;
            } else {
                currentWeightSum += meat.weight;
                weightSum += meat.weight;
                currentPrice += meat.price;
            }

            if(weightSum >= M) {
//                System.out.println(currentWeightSum + ", " + lastWeightSum + ", " + currentPrice + ", " + (weightSum - currentWeightSum + meat.weight));
                min = Math.min(min, currentPrice);
            }

//            meat.weight += lastWeightSum;
//            System.out.println(weightSum + ", " + lastWeightSum + ", " + currentWeight + ", " + currentPrice + ", " + currentWeightSum);
        }
//        System.out.println(meats);
        System.out.println(min);
    }
}
