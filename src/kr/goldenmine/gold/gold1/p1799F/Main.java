package kr.goldenmine.gold.gold1.p1799F;

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

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point[] directions = {
            new Point(-1, -1),
            new Point(-1,  1),
            new Point( 1, -1),
            new Point( 1,  1),
    };

//    public static void ban(int X, int Y, int[][] visited, int x, int y, int plus) {
//        for(int i = 0; i < 4; i++) {
//            Point direction = directions[i];
//
//            int value = 1;
//            while (true) {
//                int dx = direction.x * value;
//                int dy = direction.y * value;
//
//                if (x + dx >= 0 && y + dy >= 0 && x + dx < X && y + dy < Y) {
//                    visited[y + dy][x + dx] += plus;
//                } else {
//                    break;
//                }
//
//                value++;
//            }
//        }
//    }

    public static boolean isAble(int X, int Y, int[][] visited, int x, int y) {
        for(int i=0; i < 4; i ++) {
            Point direction = directions[i];
            int yy = direction.y + y;
            int xx = direction.x + x;

            for(int j=1; j <= X; j++) {
                if(yy >= 0 && xx >= 0 && yy < Y && xx < X) {
                    if(visited[yy][xx] == 1) return false;

                    yy += direction.y;
                    xx += direction.x;
                }
            }
        }
        return true;
    }

    public static int count(int X, int Y, int[][] arr) {
        int c = 0;
        for(int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if(arr[y][x] == -1) {
                    c++;
                }
            }
        }

        return c;
    }

    static int max = 0;
//    static int count = 0;

    public static void find(int X, int Y, int[][] arr, int[][] visited, int x, int y, int count) {
        /*
        visited -1 -> 비숍을 놓음
        visited 0 -> 비숍을 놓지 않음
        visited 양수 -> n개의 경로와 대각선이 겹침

        DFS
         */
//        count++;

        max = Math.max(max, count);

        if(x >= X) {
            x -= X;
            y++;
        }

        if(y >= Y) {
            return;
        }


//        System.out.println(x + ", " + y);
//        for(int yy = 0; yy < Y; yy++) {
//            for(int xx = 0; xx < X; xx++) {
//                System.out.print(visited[yy][xx] + " ");
//            }
//            System.out.println();
//        }

//        try {
//            Thread.sleep(200L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        if(visited[y][x] == 0 && arr[y][x] == 1 && isAble(X, Y, visited, x, y)) { // 비숍을 놓을 수는 있는 상태
            // 비숍을 놓는다
            visited[y][x] = 1;

            // 다음 좌표를 향해 이동
            find(X, Y, arr, visited, x + 2, y, count + 1);

            //비숍을 놓지 않는다
            visited[y][x] = 0;
        }

        // 다음 좌표를 향해 이동
        find(X, Y, arr, visited, x + 2, y, count);
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[][] arr = new int[N][N];
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        int[][] visited = new int[N][N];
        find(N, N, arr, visited, 0, 0, 0);

        int max1 = max;
        max = 0;
        int[][] visited2 = new int[N][N];
        find(N, N, arr, visited2, 1, 0, 0);

        System.out.println(max1 + max);
//        System.out.println(max1);
    }
}
