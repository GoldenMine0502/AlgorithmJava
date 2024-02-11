package kr.goldenmine.baekjoon.silver.silver2.p11286S_priority_queue;

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
                int abs1 = Math.abs(o1);
                int abs2 = Math.abs(o2);

                if(abs1 == abs2) {
                    return Integer.compare(o1, o2);
                }
                return Integer.compare(abs1, abs2);
            }
        });


        int N = scan.nextInt();

//        MinAbsPriorityQueue priorityQueue = new MinAbsPriorityQueue(N);

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

//            System.out.println(Arrays.toString(priorityQueue.arr));
        }
    }

//    static class MinAbsPriorityQueue {
//        int[] arr;
//
//        int size;
//
//        public MinAbsPriorityQueue(int size) {
//            arr = new int[size + 1];
//        }
//
//        public void add(int value) {
//            int current = ++size;
//            while(current != 1 && Math.abs(value) < Math.abs(arr[current/2])) {
//                arr[current] = arr[current / 2];
//                current /= 2;
//            }
//            arr[current] = value;
//        }
//
//        public int remove() {
//            int value = arr[1];
//            int temp = arr[size--];
//
//            int parent = 1;
//            int child = parent * 2;
//
//            while (child <= size) {
//                if ((child < size) && (Math.abs(arr[child]) > Math.abs(arr[child + 1])));
//                    child++;
//
//                if (Math.abs(value) <= Math.abs(arr[child]))
//                    break;
//
//                arr[parent] = arr[child];
//                parent = child;
//                child *= 2;
//            }
//
//            arr[parent] = temp;
//
//            return value;
//        }
//
//        public int size() {
//            return size;
//        }
//    }
}
