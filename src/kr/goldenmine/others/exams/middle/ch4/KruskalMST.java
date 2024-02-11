package kr.goldenmine.others.exams.middle.ch4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {
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
    public static void main(String[] args) {
        int V = 0;
        int E = 0;

        List<Data> graph = new ArrayList<>();
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

