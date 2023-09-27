package kr.goldenmine.platinum.platinum4.p12094A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void process(int[] buffer) {
        // 왼쪽에서 오른쪽 고정
        // 오른쪽의 값들은 나랑 같은 왼쪽 값을 찾아야 함.
        for(int index = buffer.length - 1; index >= 0; index--) {
            int value = buffer[index];

            boolean find = false;

            int front = index - 1;
            for(; front >= 0; front--) {
                if(buffer[front] == value) {
                    find = true;
                    break;
                } else if(buffer[front] > 0) {
                    break;
                }
            }

            if(find) {
                buffer[index] *= 2;
                buffer[front] = 0;
            }

            index = front + 1;
        }

        // 0 밀기
        int zero = 0;

        for(int index = buffer.length - 1; index >= 0; index--) {
            int value = buffer[index];
            if(value > 0) {
                buffer[index] = 0;
                buffer[(buffer.length - 1 - zero++)] = value;
            }
        }
    }

    public static int[][] right(int N, int[] buffer, int[][] previous) {
        int[][] current = new int[N][N];
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                buffer[x] = previous[y][x];
            }
            process(buffer);
            for(int x = 0; x < N; x++) {
                current[y][x] = buffer[x];
            }
        }

        return current;
    }

    public static int[][] left(int N, int[] buffer, int[][] previous) {
        int[][] current = new int[N][N];
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                buffer[N - x - 1] = previous[y][x];
            }
            process(buffer);
            for(int x = 0; x < N; x++) {
                current[y][x] = buffer[N - x - 1];
            }
        }

        return current;
    }

    public static int[][] up(int N, int[] buffer, int[][] previous) {
        int[][] current = new int[N][N];
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                buffer[N - y - 1] = previous[y][x];
            }
            process(buffer);
            for(int y = 0; y < N; y++) {
                current[y][x] = buffer[N - y - 1];
            }
        }

        return current;
    }

    public static int[][] down(int N, int[] buffer, int[][] previous) {
        int[][] current = new int[N][N];
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                buffer[y] = previous[y][x];
            }
            process(buffer);
            for(int y = 0; y < N; y++) {
                current[y][x] = buffer[y];
            }
        }

        return current;
    }

    public static int maxValue(int N, int[][] arr) {
        int max = 0;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                max = Math.max(max, arr[y][x]);
            }
        }

        return max;
    }

    public static boolean allSame(int N, int[][] arr, int[][] arr2) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] != arr2[i][j]) return false;
            }
        }
        return true;
    }

    static class Node {
        int[][] arr;
        int depth;
/*
2 4 8 16 32
2 4 8 16 32
2 4 8 8
2 4 8
2 4 8
2 4
2 4
2 4
2 4
2 4
2
2
2
2
2
2
2
2
2
2

 */
        public Node(int[][] current, int depth) {
            this.arr = current;
            this.depth = depth;
        }
    }

    static int N;
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        N = scan.nextInt();
        buffer = new int[N];
        /*
3
2 4 8
2 4 8
2 4 8

20
64 64 32 16 256 0 32 0 512 0 0 128 0 0 0 128 16 64 16 0
16 16 64 0 0 128 64 0 0 0 8 0 256 0 512 128 32 0 16 32
0 128 0 0 0 32 32 64 0 0 0 0 0 32 16 64 0 16 0 0
0 0 0 128 0 128 512 0 0 32 256 32 64 0 32 0 64 0 0 0
0 0 0 16 0 16 0 0 32 0 32 0 0 0 64 0 64 64 0 64
16 0 64 32 128 64 0 128 16 0 0 0 64 0 0 128 0 64 0 128
0 64 32 64 64 0 0 0 128 0 0 0 512 32 0 64 0 0 64 0
0 0 0 0 0 8 256 0 0 32 0 0 0 0 0 8 128 16 0 64
0 8 0 16 0 0 0 128 0 16 16 0 32 0 0 0 0 64 128 128
0 16 16 32 64 0 64 256 0 16 128 128 0 0 64 0 16 0 0 0
0 0 0 16 0 0 0 0 32 0 8 16 128 64 256 0 32 16 128 0
0 256 16 256 0 16 32 8 128 64 0 32 32 512 0 16 0 8 16 0
64 8 16 0 0 0 0 0 128 64 0 128 0 0 8 0 16 0 0 0
0 0 0 0 32 64 0 8 0 0 32 0 0 0 16 128 256 0 128 0
64 8 0 16 32 0 0 0 8 64 64 0 0 0 0 256 64 0 16 0
0 0 32 0 32 32 8 0 0 0 64 0 32 0 0 16 64 128 0 32
0 0 8 64 0 64 0 0 128 64 64 0 0 0 0 32 128 0 128 16
0 0 0 0 32 0 0 32 32 0 16 0 128 0 0 0 0 256 0 32
16 256 0 64 0 16 0 64 0 32 64 32 0 0 0 64 32 64 0 0
16 8 0 32 64 256 0 0 0 128 8 0 32 0 64 0 0 0 64 128
         */

        int[][] arr = new int[N][N];

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

//        int[] buffer = new int[N];

//        arr = up(N, buffer, arr);
//
//        for(int y = 0; y < N; y++) {
//            for(int x = 0; x < N; x++) {
//                System.out.print(arr[y][x] + " ");
//            }
//            System.out.println();
//        }

        Node start = new Node(arr, 0);

//        Stack<Node> queue = new Stack<>();
//        queue.add(start);


//        int[] maxes = new int[11];
//        int max = 0;
//
//        int count = 0;
//
//        while(!queue.isEmpty()) {
//            count++;
////            Node node = queue.poll();
//            Node node = queue.pop();
////            maxes[node.depth] = Math.max(maxes[node.depth], maxValue(N, node.arr));
//
////            System.out.print(node.depth + ":");
////            for(int i = 0; i <= 10; i++) {
////                System.out.print(maxes[i] + " ");
////            }
////            System.out.println();
//
//            int currentMax = maxValue(N, node.arr);
//
//            if(node.depth == 10) {
////                continue;
//                max = Math.max(max, currentMax);
//            } else {
//                Node up = new Node(up(N, buffer, node.arr), node.depth + 1);
//                maxes[up.depth] = Math.max(maxes[up.depth], maxValue(N, up.arr));
//
//                if(!allSame(N, node.arr, up.arr) && maxes[up.depth] / Math.pow(2, up.depth - node.depth) <= currentMax)
//                    queue.add(up);
//                else
//                    max = Math.max(max, currentMax);
//
//                Node down = new Node(down(N, buffer, node.arr), node.depth + 1);
//                maxes[down.depth] = Math.max(maxes[down.depth], maxValue(N, down.arr));
//
//                if(!allSame(N, node.arr, down.arr) && maxes[down.depth] / Math.pow(2, down.depth - node.depth) <= currentMax)
//                    queue.add(down);
//                else
//                    max = Math.max(max, currentMax);
//
//                Node left = new Node(left(N, buffer, node.arr), node.depth + 1);
//                maxes[left.depth] = Math.max(maxes[left.depth], maxValue(N, left.arr));
//
//                if(!allSame(N, node.arr, left.arr) && maxes[left.depth] / Math.pow(2, left.depth - node.depth) <= currentMax)
//                    queue.add(left);
//                else
//                    max = Math.max(max, currentMax);
//
//                Node right = new Node(right(N, buffer, node.arr), node.depth + 1);
//                maxes[right.depth] = Math.max(maxes[right.depth], maxValue(N, right.arr));
//
//                if(!allSame(N, node.arr, right.arr) && maxes[right.depth] / Math.pow(2, right.depth - node.depth) <= currentMax)
//                    queue.add(right);
//                else
//                    max = Math.max(max, currentMax);
//            }
//        }

        solve(start);
        int max = 0;
//        System.out.println(count);
        for(int i = 0; i <= 10; i++) {
            max = Math.max(max, maxes[i]);
        }
//        System.out.println(count);
        System.out.println(max);
    }

    static int count = 0;
    static int[] buffer;
    static int[] maxes = new int[11];

    public static void solve(Node node) {
        count++;
        int currentMax = maxValue(N, node.arr);

        if(node.depth == 10) {
            maxes[10] = Math.max(maxes[10], currentMax);
            return;
        }

        Node up = new Node(up(N, buffer, node.arr), node.depth + 1);
        Node down = new Node(down(N, buffer, node.arr), node.depth + 1);
        Node left = new Node(left(N, buffer, node.arr), node.depth + 1);
        Node right = new Node(right(N, buffer, node.arr), node.depth + 1);

        maxes[up.depth] = Math.max(maxes[up.depth], maxValue(N, up.arr));
        maxes[down.depth] = Math.max(maxes[down.depth], maxValue(N, down.arr));
        maxes[left.depth] = Math.max(maxes[left.depth], maxValue(N, left.arr));
        maxes[right.depth] = Math.max(maxes[right.depth], maxValue(N, right.arr));

        if(!allSame(N, node.arr, up.arr) && maxes[up.depth] / Math.pow(2, up.depth - node.depth) <= currentMax)
            solve(up);
        if(!allSame(N, node.arr, down.arr) && maxes[down.depth] / Math.pow(2, down.depth - node.depth) <= currentMax)
            solve(down);
        if(!allSame(N, node.arr, left.arr) && maxes[left.depth] / Math.pow(2, left.depth - node.depth) <= currentMax)
            solve(left);
        if(!allSame(N, node.arr, right.arr) && maxes[right.depth] / Math.pow(2, right.depth - node.depth) <= currentMax)
            solve(right);
    }
}
