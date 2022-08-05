package kr.goldenmine.platinum.platinum5.p3197F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
        int birdContain;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int birdContain) {
            this(x, y);
            this.birdContain = birdContain;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y, birdContain + p.birdContain);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    static class Counter {
        int count = 0;
    }

    static class MyBoolean {
        boolean value;
    }

    public static void bfs(int X, int Y, boolean[][] visited, List<Point> starts, Predicate<Point> isQueueAdd, Predicate<Point> isExpand, Consumer<Point> onPolled) {
        Queue<Point> points = new LinkedList<>();

        for (int i = 0; i < starts.size(); i++) {
            Point start = starts.get(i);
            points.add(start);
            visited[start.y][start.x] = true;
        }

        while (!points.isEmpty()) {
            Point p = points.poll();

            onPolled.accept(p);

            if (isExpand.test(p)) {
                loopDirections(X, Y, p, (next) -> {
                    if (isQueueAdd.test(next)) {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                });
            }
        }
    }

    public static void loopDirections(int X, int Y, Point point, Consumer<Point> onAdded) {
        for (int i = 0; i < directions.length; i++) {
            Point next = point.add(directions[i]);

            if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                onAdded.accept(next);
            }
        }
    }

    public static boolean isMeet(char[][] arr, boolean[][] visitedBirds, Point bird, int X, int Y) {
//        boolean[][] visited = new boolean[Y][X];
        Predicate<Point> isExpand = it -> arr[it.y][it.x] == '.' || arr[it.y][it.x] == 'L';

        MyBoolean b = new MyBoolean();

        bfs(X, Y, visitedBirds, Arrays.asList(bird), it -> true, isExpand, it -> {
            if (bird != it && arr[it.y][it.x] == 'L') {
                b.value = true;
            }
        });

        return b.value;
    }

    public static List<Point> getSurface(char[][] arr, boolean[][] visited, List<Point> birds, int X, int Y) {
        List<Point> surfaces = new ArrayList<>();

        Consumer<Point> consumer = it -> {
            if (arr[it.y][it.x] == 'X') {
                Counter counter = new Counter();

                loopDirections(X, Y, it, next -> {
                    if (arr[next.y][next.x] == 'X') {
                        counter.count++;
                    }
                });

                if (counter.count >= 1) {
                    surfaces.add(it);
                }
            }
        };

        Predicate<Point> isExpand = it -> arr[it.y][it.x] == '.' || arr[it.y][it.x] == 'L';

        bfs(X, Y, visited, birds, it -> true, isExpand, consumer);

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (arr[y][x] == '.') {
                    bfs(X, Y, visited, Arrays.asList(new Point(x, y)), it -> true, isExpand, consumer);
                }
            }
        }

        return surfaces;
    }

    public static void main(String[] args) throws InterruptedException {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        char[][] arr = new char[Y][X];

        List<Point> birds = new ArrayList<>();

        for (int y = 0; y < Y; y++) {
            String text = scan.nextLine();

            for (int x = 0; x < X; x++) {
                arr[y][x] = text.charAt(x);
                if (arr[y][x] == 'L') {
                    birds.add(new Point(x, y, birds.size() + 1));
                }
            }
        }


        boolean[][] visited = new boolean[Y][X];
        boolean[][] visitedBirds = new boolean[Y][X];
//        System.out.println();

        List<Point> surfaces = getSurface(arr, visited, birds, X, Y);
        List<Point> nextSurfaces = new ArrayList<>();
        List<Point> checks = new ArrayList<>();
        List<Point> checks2 = new ArrayList<>();

        int day = 0;

//        if (!isMeet(arr, birds.get(0), X, Y)) {
        while (true) {
//            System.out.println("a");
            MyBoolean b = new MyBoolean();

//            if (isMeet(arr, visitedBirds, birds.get(0), X, Y)) {
//                break;
//            }

            surfaces.forEach(it -> {
                arr[it.y][it.x] = '.';
                if (it.birdContain == 1) {
                    arr[it.y][it.x] = '1';
                } else if (it.birdContain == 2) {
                    arr[it.y][it.x] = '2';
                }
                loopDirections(X, Y, it, next -> {
                    if (arr[next.y][next.x] == 'X') {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            nextSurfaces.add(next);

//                            nextSurfacesPreviousBirdTypes.add(it.birdContain);
//                            System.out.println(it.x + ", " + it.y + ", " + it.birdContain + ", " + next.x + ", " + next.y + ", " + next.birdContain);
//                            System.out.println(arr[it.y][it.x] + ", " + arr[next.y][next.x] + " v");
                        } else {
//                            System.out.println(it.x + ", " + it.y + ", " + it.birdContain + ", " + next.x + ", " + next.y + ", " + next.birdContain);
//                            System.out.println(arr[it.y][it.x] + ", " + arr[next.y][next.x]);
//                            if(next.birdContain > 0) {
////                                b.value = true;
//                            }

                            checks.add(it);
                            checks2.add(next);
                        }

//                            int myBird = next.birdContain;
//
//                            if(myBird > 0) {
//                                loopDirections(X, Y, next, nextnext -> {
//                                    if(arr[it.y][it.x] == 1 || arr[it.y][it.x] == 2) {
//                                        if (arr[it.y][it.x] != myBird) {
//                                            b.set(true);
//                                        }
//                                    }
//                                });
//                            }
                    }
                });
//                    else {
//                        arr[it.y][it.x] = 'K';
//                    }
            });

            for (int i = 0; i < checks.size(); i++) {
                Point p = checks.get(i);
                Point p2 = checks2.get(i);
//                System.out.println(p.x + ", " + p.y + ", " + birdType + ", " + arr[p.y][p.x]);
                if((arr[p.y][p.x] == '1' || arr[p.y][p.x] == '2') && (arr[p2.y][p2.x] == '1' || arr[p2.y][p2.x] == '2')) {
                    if (arr[p.y][p.x] != arr[p2.y][p2.x]) {
                        b.value = true;
                    }
                }
            }

//            for (int y = 0; y < Y; y++) {
//                for (int x = 0; x < X; x++) {
//                    System.out.print(arr[y][x] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//                System.out.println(b.value);

//            if(nextSurfaces.size() == 0) {
//                if(isMeet(arr, visitedBirds, birds.get(0), X, Y)) {
//                    break;
//                }
//            }

            surfaces.clear();
            surfaces.addAll(nextSurfaces);

            checks.clear();
            checks2.clear();

            nextSurfaces.clear();
//            nextSurfacesPreviousBirdTypes.clear();

            day++;

//            Thread.sleep(3000L);

            if (b.value) break;
        }
//        }

        System.out.println(day);


    }
}
