package kr.goldenmine.baekjoon.gold.gold2.p1202A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
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
            jewels[i] = new Jewel(scan.nextInt(), scan.nextInt());
        }

        int[] bags = new int[K];
        for(int i = 0; i < K; i++) {
            bags[i] = scan.nextInt();
        }

        Arrays.sort(jewels, (o1, o2) -> {
            int c1 = Integer.compare(o1.weight, o2.weight);
            if(c1 == 0) {
                return Integer.compare(o1.value, o2.value);
            }

            return c1;
        });

        Arrays.sort(bags);
        PriorityQueue<Jewel> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -o.value));

        int index = 0;
        long sum = 0;
        for(int i = 0; i < K; i++) {
            while(index < N && bags[i] >= jewels[index].weight) {
                queue.add(jewels[index]);
                index++;
            }
            if(!queue.isEmpty()) {
                sum += queue.poll().value;
            }
        }

        System.out.println(sum);
    }
}
