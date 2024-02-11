package kr.goldenmine.baekjoon.gold.gold4.p1197S_MST_kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

//    static class Node {
//        int dest;
//        int weight;
//
//        public Node(int dest, int weight) {
//            this.dest = dest;
//            this.weight = weight;
//        }
//    }

    static class Data {
        int a;
        int b;
        int weight;

        public Data(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    // https://www.weeklyps.com/entry/%ED%94%84%EB%A6%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Prims-algorithm
    //
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int V = scan.nextInt();
        int E = scan.nextInt();

        List<Data> graph = new ArrayList<>();
        for(int i = 0; i < E; i++) {
            graph.add(new Data(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
//        List<List<Node>> nodes = new ArrayList<>();
//
//        for(int i = 0; i <= V; i++) {
//            nodes.add(new ArrayList<>());
//        }
//
//        for(int i = 0; i < E; i++) {
//            int start = scan.nextInt();
//            int finish = scan.nextInt();
//            int weight = scan.nextInt();
//
//            nodes.get(start).add(new Node(finish, weight));
//            nodes.get(finish).add(new Node(start, weight));
//        }

        graph.sort(Comparator.comparingInt(o -> o.weight));

        int[] parent = new int[V + 1];
        for(int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        int total = 0;

        for(int i = 0; i < E; i++) {
            Data data = graph.get(i);

            if(find(parent, data.a) != find(parent, data.b)) {
                merge(parent, data.a, data.b);

                total += data.weight;
            }
        }

        System.out.println(total);
    }

    public static int find(int[] arr, int value) {
        if(arr[value] == value)
            return value;
        return arr[value] = find(arr, arr[value]);
    }

    public static void merge(int[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);
        if(x == y) return;
        arr[y] = x;
    }

    public static boolean isUnion(int[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);

        return x == y;
    }
}
