package kr.goldenmine.baekjoon.gold.gold5.p1068;

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

    static class Tree {
        int i;
        List<Tree> childs = new ArrayList<Tree>();

        Tree(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "idx: " + i + ", childs: " + childs;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        Tree[] parents = new Tree[N + 1];
        Tree root = null;
        for(int i = 0; i < N; i++) {
            parents[i] = new Tree(i);
        }

        for(int i =0 ; i < N; i++) {
            int child = i;
            int parent = scan.nextInt();

            // -1이면 root node
            if(parent != -1) {
                parents[parent].childs.add(parents[child]);
            } else {
                root = parents[i];
            }
        }

        int deleted = scan.nextInt();

//        for(int i = 0; i< N; i++) {
//            System.out.println(i + ", " + parents[i].childs);
//        }

        Queue<Integer> points = new LinkedList<>();
        boolean[] visited = new boolean[N];

        points.add(root.i);
        visited[root.i] = true;

        int count = 0;

//        if(root.i == deleted) {
//            System.out.println(0);
//            return;
//        }

        while (!points.isEmpty()) {
            int p = points.poll();

            if(p == deleted) continue;

            parents[p].childs.remove(parents[deleted]);
            if(parents[p].childs.isEmpty()) {
                count++;
                continue;
            }

            for(Tree child : parents[p].childs) {
                if(!visited[child.i] && child.i != deleted) {
                    visited[child.i] = true;
                    points.add(child.i);
                }
            }
        }

        System.out.println(count);
    }
}
