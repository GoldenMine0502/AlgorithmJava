package kr.goldenmine.baekjoon.gold.gold1.p13460;

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
        char[][] arr;
        int count;
        int depth;
        int previous;

        public Node(char[][] current, int depth, int previous, int count) {
            this.arr = current;
            this.depth = depth;
            this.previous = previous;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
//                    "arr=" + Arrays.toString(arr) +
                    "count=" + count +
                    ", depth=" + depth +
                    ", previous=" + previous +
                    '}';
        }

        public void print() {
            System.out.println(this);

            for(int y = 0; y < arr.length; y++) {
                for(int x = 0; x < arr[y].length; x++) {
                    System.out.print(arr[y][x] + " ");
                }
                System.out.println();
            }
        }
    }

    // 0 리턴 = 아무것도 안 빠짐
    // 1 리턴 = 빨강 빠짐
    // 2 리턴 = 파랑 빠짐
    // 3 리턴 = 둘 다 빠짐
    public static int process(char[] buffer) {
        // 한칸씩 옮겨보는게 나을듯. N^2로 풀이
        // #RO.#

        // 그럼 최대 1사이클에 100번 연산.
        // 근데 10사이클이면 4^10회 = 1048576
        // 1사이클의 연산 횟수를 줄일 필요가 있다.
        // 근데 한번 간 방향으로 또 갈 필요가 있을까?
        // 그래서 3^10 = 약 5만회가 된다.

        boolean red = false;
        boolean blue = false;

        for(int i = buffer.length - 1; i >= 0; i--) {
            if(buffer[i] == 'R' || buffer[i] == 'B') {

                for(int j = i + 1; j < buffer.length; j++) {
                    if(buffer[j] == 'O') { // 구멍 발견?
                        if(buffer[i] == 'R') red = true;
                        if(buffer[i] == 'B') blue = true;

                        buffer[i] = '.';

                        break;
                    }

                    if(buffer[j] != '.') { // swap
                        char previous = buffer[j - 1];
                        buffer[j - 1] = buffer[i];
                        buffer[i] = previous;

                        break;
                    }
                }
            }
        }

//        if((red ? 1 : 0) + (blue ? 2 : 0) > 0) {
//            System.out.println((red ? 1 : 0) + (blue ? 2 : 0));
//        }
        // bitmask
        return (red ? 1 : 0) + (blue ? 2 : 0);
    }

    public static Node right(int X, int Y, char[] buffer, Node previous) {
        int count = 0;

        char[][] current = new char[Y][X];
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                buffer[x] = previous.arr[y][x];
            }
            count |= process(buffer);
            for (int x = 0; x < X; x++) {
                current[y][x] = buffer[x];
            }
        }

        return new Node(current, previous.depth + 1, 0, count);
    }

    public static Node left(int X, int Y, char[] buffer, Node previous) {
        int count = 0;

        char[][] current = new char[Y][X];
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                buffer[X - x - 1] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int x = 0; x < X; x++) {
                current[y][x] = buffer[X - x - 1];
            }
        }

//        System.out.println("L" + count);

        return new Node(current, previous.depth + 1, 1, count);
    }

    public static Node up(int X, int Y, char[] buffer, Node previous) {
        int count = 0;

        char[][] current = new char[Y][X];
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                buffer[Y - y - 1] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int y = 0; y < Y; y++) {
                current[y][x] = buffer[Y - y - 1];
            }
        }

//        System.out.println("U" + count);

        return new Node(current, previous.depth + 1, 2, count);
    }

    public static Node down(int X, int Y, char[] buffer, Node previous) {
        int count = 0;

        char[][] current = new char[Y][X];
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                buffer[y] = previous.arr[y][x];
            }
            count += process(buffer);
            for (int y = 0; y < Y; y++) {
                current[y][x] = buffer[y];
            }
        }

//        System.out.println("D" + count);

        return new Node(current, previous.depth + 1, 3, count);
    }
    /*
3 7
#######
#B.O.R#
#######
ANS = 1

3 3
###
#0#
###
ANS = -1

8 8
########
#.####.#
#...#B##
#.##.R.#
######.#
##.##O.#
###.##.#
########
ANS = 7

4 6
######
#R####
#B..O#
######
ANS = -1

4 6
######
#R#O##
#B...#
######
ANS = 4
     */
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        char[][] arr = new char[Y][X];

        for(int y = 0; y < Y; y++) {
            String line = scan.next();

            for(int x = 0; x < X; x++) {
                arr[y][x] = line.charAt(x);
            }
        }

        char[] bufferY = new char[Y];
        char[] bufferX = new char[X];

//        Node node = new Node(arr, 0, -1, 0);
//        node.print();
//
//        node = left(X, Y, bufferX, node);
//        node.print();
//
//        node = down(X, Y, bufferY, node);
//        node.print();
//
//        node = right(X, Y, bufferX, node);
//        node.print();
//
//        node = down(X, Y, bufferY, node);
//        node.print();
//
//        node = right(X, Y, bufferY, node);
//        node.print();
//
//        node = down(X, Y, bufferY, node);
//        node.print();
//
//        node = left(X, Y, bufferY, node);
//        node.print();
        bfs(X, Y, arr, bufferX, bufferY);
    }

    public static void bfs(int X, int Y, char[][] arr, char[] bufferX, char[] bufferY) {

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(new Node(arr, 0, -1, 0));

//        int blueFound = -1;

        while(!nodes.isEmpty()) {
            Node node = nodes.poll();

//            System.out.println(node);

//            if(blueFound != -1 && blueFound < node.depth) {
//                break;
//            }


//            for(int y = 0; y < Y; y++) {
//                for(int x = 0; x < X; x++) {
//                    System.out.print(node.arr[y][x] + " ");
//                }
//                System.out.println();
//            }

            if(node.count != 0) {
                if(node.count == 1) {
                    System.out.println(node.depth);
                    return;
                } else {
//                    if(blueFound == -1) {
//                        blueFound = node.depth;
//                        node.print();
//                        System.out.println("BLUE");
//                    }

                    continue;
                }
            }

            // next
            if(node.depth < 10) {
                if(node.previous != 0) {
                    Node right = right(X, Y, bufferX, node);
                    nodes.add(right);
                }

                if(node.previous != 1) {
                    Node left = left(X, Y, bufferX, node);
                    nodes.add(left);
                }

                if(node.previous != 2) {
                    Node up = up(X, Y, bufferY, node);
                    nodes.add(up);
                }

                if(node.previous != 3) {
                    Node down = down(X, Y, bufferY, node);
                    nodes.add(down);
                }
            }
        }

        System.out.println(-1);
    }
}
