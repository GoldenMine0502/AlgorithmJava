package kr.goldenmine.gold.gold4.p1707;

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

    static class Node {
        int value;
        int color;

        public Node(int value, int color) {
            this.value = value;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            int V = scan.nextInt();
            int E = scan.nextInt();

            int[] points = new int[V + 1];

            List<List<Integer>> nodes = new ArrayList<>();

            for(int j = 0; j <= V; j++) {
                nodes.add(new ArrayList<>());
            }

            for(int j = 0; j < E; j++) {
                int start = scan.nextInt();
                int finish = scan.nextInt();

                nodes.get(start).add(finish);
                nodes.get(finish).add(start);
            }

            //이분 그래프의 여부를 확인하는 방법에는 BFS와 DFS 모두 사용할 수 있는데, 두 방법 모두 결과적으로는
            //"모든 인접한 정점이 서로 다른 색으로 칠해지면 이분 그래프"이고, 그렇지 않으면 이분 그래프가 아니다.

            boolean binary = true;

            for(int j = 1; j <= V; j++) {
                if(points[j] == 0) {
//                    System.out.println(Arrays.toString(points));
                    Queue<Node> queue = new LinkedList<>();
                    // 뭐 시작 정점은 아무거나 해도 되지 않나.
                    queue.add(new Node(j, 1)); // 1 = 빨강, 2 = 파랑
                    points[j] = 1;


                    while(!queue.isEmpty()) {
                        Node value = queue.poll();

                        List<Integer> nexts = nodes.get(value.value);

                        for(int k = 0; k < nexts.size(); k++) {
                            Node next = new Node(nexts.get(k), value.color == 1 ? 2 : 1); // swap color

                            switch(points[next.value]) {
                                case 0: // 방문하지 않은 경우
                                    points[next.value] = next.color;
                                    queue.add(next);
                                    break;
                                default:
                                    if(points[next.value] != next.color) {
                                        binary = false;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
            if(binary) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
