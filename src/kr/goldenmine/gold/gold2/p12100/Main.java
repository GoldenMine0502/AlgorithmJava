package kr.goldenmine.gold.gold2.p12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

    public static int process(int[] buffer) {
        int count = 0;

        // 왼쪽에서 오른쪽 고정
        // 오른쪽의 값들은 나랑 같은 왼쪽 값을 찾아야 함.
        for (int index = buffer.length - 1; index >= 0; index--) {
            int value = buffer[index];

            if (value > 0) {
                boolean find = false;

                int front = index - 1;
                for (; front >= 0; front--) {
                    if (buffer[front] == value) {
                        find = true;
                        break;
                    } else if (buffer[front] > 0) {
                        break;
                    }
                }

                if (find) {
                    buffer[index] *= 2;
                    buffer[front] = 0;
                    count++;
                }

                index = front + 1;
            }
        }

        // 0 밀기
        int zero = 0;

        for (int index = buffer.length - 1; index >= 0; index--) {
            int value = buffer[index];
            if (value > 0) {
                buffer[index] = 0;
                buffer[(buffer.length - 1 - zero++)] = value;
                count++;
            }
        }

        return count;
    }

    public static Node right(int N, int[] buffer, Node previous) {
        int count = 0;

        int[][] current = new int[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                buffer[x] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int x = 0; x < N; x++) {
                current[y][x] = buffer[x];
            }
        }

//        System.out.println("R" + count);

        return new Node(current, previous.depth + 1, 0, count);
    }

    public static Node left(int N, int[] buffer, Node previous) {
        int count = 0;

        int[][] current = new int[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                buffer[N - x - 1] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int x = 0; x < N; x++) {
                current[y][x] = buffer[N - x - 1];
            }
        }

//        System.out.println("L" + count);

        return new Node(current, previous.depth + 1, 1, count);
    }

    public static Node up(int N, int[] buffer, Node previous) {
        int count = 0;

        int[][] current = new int[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                buffer[N - y - 1] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int y = 0; y < N; y++) {
                current[y][x] = buffer[N - y - 1];
            }
        }

//        System.out.println("U" + count);

        return new Node(current, previous.depth + 1, 2, count);
    }

    public static Node down(int N, int[] buffer, Node previous) {
        int count = 0;

        int[][] current = new int[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                buffer[y] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int y = 0; y < N; y++) {
                current[y][x] = buffer[y];
            }
        }

//        System.out.println("D" + count);

        return new Node(current, previous.depth + 1, 3, count);
    }

    public static int maxValue(int N, int[][] arr) {
        int max = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                max = Math.max(max, arr[y][x]);
            }
        }

        return max;
    }

    static class Node {
        int[][] arr;
        int count;
        int depth;
        int previous;

        public Node(int[][] current, int depth, int previous, int count) {
            this.arr = current;
            this.depth = depth;
            this.previous = previous;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        /*
3
2 4 8
2 4 8
2 4 8
         */

        int[][] arr = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        int[] buffer = new int[N];

//        arr = up(N, buffer, arr);
//
//        for(int y = 0; y < N; y++) {
//            for(int x = 0; x < N; x++) {
//                System.out.print(arr[y][x] + " ");
//            }
//            System.out.println();
//        }

        Node start = new Node(arr, 0, -1, 0);

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        int max = 0;

        // 44444444
        // 큐 자체를 적게 돌아가게 하던가..
        // 배열 복사를 안하게 하던가..

        while (!queue.isEmpty()) {
            Node node = queue.poll();

//            max = Math.max(max, maxValue(N, node.arr));

            if (node.depth == 5) {
                max = Math.max(max, maxValue(N, node.arr));
            } else {
                int total = 0;
//                if (node.previous != 0) {
                Node up = up(N, buffer, node);

                if (up.count > 0)
                {
                    queue.add(up);
                    total++;
                }
//                }
//                if (node.previous != 1) {
                Node down = down(N, buffer, node);
                if (down.count > 0)
                {
                    queue.add(down);
                    total++;
                }
//                }
//                if (node.previous != 2) {
                Node left = left(N, buffer, node);
                if (left.count > 0)
                {
                    queue.add(left);
                    total++;
                }
//                }
//                if (node.previous != 3) {
                Node right = right(N, buffer, node);
                if (right.count > 0)
                {
                    queue.add(right);
                    total++;
                }

                if(total == 0) {
                    max = Math.max(max, maxValue(N, node.arr));
                }
//                }
            }
        }

        System.out.println(max);
    }
}
