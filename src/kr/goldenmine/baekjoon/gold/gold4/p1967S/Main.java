package kr.goldenmine.baekjoon.gold.gold4.p1967S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    static class Node {
        int cost;
        int to;

        Node(int cost, int to) {
            this.cost = cost;
            this.to = to;
        }
    }

    static int maxIndex = -1;
    static int maxValue = 0;

    public static void dfs(List<List<Node>> nodes, int startIndex, boolean[] visited, int value) {
        List<Node> nexts = nodes.get(startIndex);

        visited[startIndex] = true;
        if(maxValue < value) {
            maxValue = value;
            maxIndex = startIndex;
        }


        for(Node next : nexts) {
            if(!visited[next.to]) {
//                System.out.println("start: " + startIndex + ", cost: " + (value + next.cost) + ", " + value);
                visited[next.to] = true;
                dfs(nodes, next.to, visited, value + next.cost);
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int E = scan.nextInt();

        if(E == 1) {
            System.out.println(0);
            return;
        }

        List<List<Node>> nodes = new ArrayList<>();

        // 그냥 E개 만들어잇
        for(int i = 0; i <= E + 1; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < E - 1; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int cost = scan.nextInt();

//            System.out.println(from + " " + to + " " + cost);

            // 양방향 그래프
            nodes.get(from).add(new Node(cost, to));
            nodes.get(to).add(new Node(cost, from));
        }


        dfs(nodes, 1, new boolean[nodes.size()], 0);
        int far = maxIndex;
        maxValue = 0;
        maxIndex = -1;
//        System.out.println(far);

        dfs(nodes, far, new boolean[nodes.size()], 0);
        System.out.println(maxValue);
    }
}
