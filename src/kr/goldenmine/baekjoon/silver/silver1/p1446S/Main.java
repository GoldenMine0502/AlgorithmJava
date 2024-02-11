package kr.goldenmine.baekjoon.silver.silver1.p1446S;

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

    static class Shortcut {
        int start;
        int finish;
        int cost;

        public Shortcut(int start, int finish, int cost) {
            this.start = start;
            this.finish = finish;
            this.cost = cost;
        }
    }

    static void dfs(List<Shortcut> shortcuts, int[] cost, int N, int D, int current, int x) {
        if(cost[x] < current) return;
        else cost[x] = current;

        for(int i = 0; i < shortcuts.size(); i++) {
            Shortcut shortcut = shortcuts.get(i);

            if(shortcut.start == x) {
                dfs(shortcuts, cost, N, D, current + shortcut.cost, shortcut.finish);
            }
        }

        // 앞으로 한칸 이동
        if(x < D) {
            dfs(shortcuts, cost, N, D, current + 1, x + 1);
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int D = scan.nextInt();

        List<Shortcut> shortcuts = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            Shortcut shortcut = new Shortcut(scan.nextInt(), scan.nextInt(), scan.nextInt());

            shortcuts.add(shortcut);
        }

        int[] cost = new int[10002];
        for(int i = 0; i <= 10001; i++) {
            cost[i] = Integer.MAX_VALUE;
        }

        dfs(shortcuts, cost, N, D, 0, 0);

//        for(int i = 0; i <= D; i++) {
//            System.out.println(cost[i]);
//        }
        System.out.println(cost[D]);
    }
}
