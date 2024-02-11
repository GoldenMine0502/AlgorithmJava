package kr.goldenmine.baekjoon.gold.gold4.p13913S;

import java.io.*;
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

    static class Node {
        int pos;
        int sec;

        public Node(int pos, int sec) {
            this.pos = pos;
            this.sec = sec;
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        int[] last = new int[200001];
        for(int i = 0; i <= 200000; i++) {
            last[i] = -1;
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(N, 0));

        while(!queue.isEmpty()) {
            Node node = queue.removeFirst();

            if(node.pos == K) {
                System.out.println(node.sec);
                break;
            }

            // N 최대치 곱하기 2 정도면 뭐...
            if(node.pos <= 100000 && last[2 * node.pos] == -1) {
                last[2 * node.pos] = node.pos;
                queue.addLast(new Node(2 * node.pos, node.sec + 1));
            }

            if(node.pos >= 1 && last[node.pos - 1] == -1) {
                last[node.pos - 1] = node.pos;
                queue.addLast(new Node(node.pos - 1, node.sec + 1));
            }

            if(node.pos < 200000 && last[node.pos + 1] == -1) {
                last[node.pos + 1] = node.pos;
                queue.addLast(new Node(node.pos + 1, node.sec + 1));
            }
        }

        List<Integer> lasts = new ArrayList<>();

        int add = K;

        while(add != N && add >= 0) {
            lasts.add(add);
            add = last[add];
        }

        lasts.add(N);

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = lasts.size() - 1; i >= 0; i--) {
            output.write(String.valueOf(lasts.get(i)));
            output.write(" ");
        }

        output.flush();
//        System.out.println(Arrays.toString(last));
//        System.out.println(lasts);
    }
}
