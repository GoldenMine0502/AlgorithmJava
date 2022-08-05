package kr.goldenmine.silver.silver2.p11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(Math.abs(o1), Math.abs(o2));
            }
        });

        int N = scan.nextInt();

        for(int i = 0; i < N; i++) {
            int value = scan.nextInt();

            if(value != 0) {
                priorityQueue.add(value);
            } else {
                if(priorityQueue.size() > 0) {
                    System.out.println(priorityQueue.remove());
                } else {
                    System.out.println(0);
                }
            }
        }
    }

    static class MyPriorityQueue {
        int[] arr;

        int size;

        MyPriorityQueue(int size) {
            arr = new int[size + 1];
        }

        void add(int value) {
            arr[++size] = value;

            int current = size;
            while(current > 1 && arr[current/2] > arr[current]) {
                swap(current/2, current);
                current /= 2;
            }
        }

        void swap(int index, int index2) {
            int temp = arr[index];
            arr[index] = arr[index2];
            arr[index2] = temp;
        }
    }
}
