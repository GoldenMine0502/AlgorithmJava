package kr.goldenmine.gold.gold4.p4386S;

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

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point p) {
            return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
        }
    }

    static class Node {
        int a;
        int b;
        double weight;

        public Node(int a, int b, double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        Point[] stars = new Point[N + 1];

        for(int i = 1; i <= N; i++) {
            stars[i] = new Point(scan.nextDouble(), scan.nextDouble());
        }

        List<Node> nodes = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j) {
                    nodes.add(new Node(i, j, stars[i].distance(stars[j])));
                }
            }
        }

        nodes.sort(Comparator.comparingDouble(o -> o.weight));

        int[] parent = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        double total = 0;

        for(int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            if(find(parent, node.a) != find(parent, node.b)) {
                merge(parent, node.a, node.b);

                total += node.weight;
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
