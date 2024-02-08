package kr.goldenmine.gold.gold5.p1106A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

    static class City {
        int cost;
        int gain;

        public City(int cost, int gain) {
            this.cost = cost;
            this.gain = gain;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int C = scan.nextInt();
        int N = scan.nextInt();

        List<City> cities = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            cities.add(new City(scan.nextInt(), scan.nextInt()));
        }

        int[] dp = new int[C + 101];
        Arrays.fill(dp, 99999999);
        dp[0] = 0;

        for(int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            for(int j = city.gain; j < C+101; j++){
                dp[j]=Math.min(dp[j], city.cost + dp[j - city.gain]);
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i = C; i < C + 101; i++) {
            res = Math.min(res, dp[i]);
        }
        System.out.println(res);
    }
}
