package kr.goldenmine.gold.gold4.p3190S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point point) {
            return new Point(x + point.x, y + point.y);
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;

            return x == p.x && y == p.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Direction {
        int time;
        char direction;

        public Direction(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }

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

    public static void main(String[] args) {
        // 읽기
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        Point[] apples = new Point[K];

        for (int i = 0; i < K; i++) {
            int y = scan.nextInt() - 1;
            int x = scan.nextInt() - 1;
            apples[i] = new Point(x, y);
        }

        int L = scan.nextInt();
        Direction[] directions = new Direction[L];

        for (int i = 0; i < L; i++) {
            int time = scan.nextInt();
            char direction = scan.next().charAt(0);

            directions[i] = new Direction(time, direction);
        }

        /* 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.

         */
        // solve
        ArrayDeque<Point> snake = new ArrayDeque<>();
        snake.push(new Point(0, 0));

        Point right = new Point(1, 0);
        Point bottom = new Point(0, 1);
        Point left = new Point(-1, 0);
        Point up = new Point(0, -1);

        // 오른쪽 이동
        Point currentDirection = right;

        int time = 0;
        int directionChanges = 0;
//        int appleChanges = 0;

        while(true) {
            Point lastPosition = snake.removeFirst();

            Point currentPosition = snake.size() > 0 ? snake.getLast().add(currentDirection) : lastPosition.add(currentDirection);

            time++;

            // 죽으면 게임 종료
            boolean quit = false;
            if(currentPosition.x < 0 || currentPosition.y < 0 || currentPosition.x >= N || currentPosition.y >= N)
                quit = true;

            if(lastPosition.equals(currentPosition))
                quit = true;

            for(Point body : snake) {
                if(currentPosition.equals(body)) {
                    quit = true;
                    break;
                }
            }

            if(quit) break;

//            System.out.println(lastPosition + ", " + currentPosition + ", " + snake);
            snake.addLast(currentPosition); // addLast = add
//            System.out.println(time + ", " + lastPosition + ", " + currentPosition + ", " + snake);

            // 현재 위치에 사과가 존재하는지 확인
            Point apple = null;
            for(int i = 0; i < apples.length; i++) {
//                System.out.println("apple" + apples[i]);
                if(apples[i] != null) {
                    if(currentPosition.equals(apples[i])) {
                        apple = apples[i];
                        apples[i] = null; // 사과 없애기
//                        break;
                    }
                }
            }

            // 사과가 존재하면 꼬리 살려주기
//            if(appleChanges < apples.length && currentPosition.equals(apples[appleChanges])) {
            if(apple != null) {
//                System.out.println("apple! " + lastPosition);
                snake.addFirst(lastPosition);
            }

            // 방향을 바꾸는 타이밍인지 확인
            if(directionChanges < directions.length && directions[directionChanges].time == time) {
//                System.out.println("direction! " + directions[directionChanges].direction);
                if(directions[directionChanges].direction == 'D') {
                    if(currentDirection == right) {
                        currentDirection = bottom;
                    } else if(currentDirection == bottom) {
                        currentDirection = left;
                    } else if(currentDirection == left) {
                        currentDirection = up;
                    } else {
                        currentDirection = right;
                    }
                } else {
                    if(currentDirection == right) {
                        currentDirection = up;
                    } else if(currentDirection == up) {
                        currentDirection = left;
                    } else if(currentDirection == left) {
                        currentDirection = bottom;
                    } else {
                        currentDirection = right;
                    }
                }

                directionChanges++;
            }
        }

        System.out.println(time);
    }
}
