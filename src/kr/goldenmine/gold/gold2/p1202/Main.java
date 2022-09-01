package kr.goldenmine.gold.gold2.p1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

    static class Jewel {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Jewel{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        Jewel[] jewels = new Jewel[N];
        for(int i = 0; i < N; i++) {
            int weight = scan.nextInt();
            int value = scan.nextInt();
            jewels[i] = new Jewel(weight, value);
        }

        int[] bags = new int[K];
        for(int i = 0; i < K; i++) {
            bags[i] = scan.nextInt();
        }

        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if(o1.value != o2.value) {
                    return -Integer.compare(o1.value, o2.value);
                } else {
                    return Integer.compare(o1.weight, o2.weight);
                }
            }
        });

        int[] results = new int[N];
        int index = 0;


        Arrays.sort(bags);

        System.out.println(Arrays.toString(jewels));
        System.out.println(Arrays.toString(bags));
    }
}
