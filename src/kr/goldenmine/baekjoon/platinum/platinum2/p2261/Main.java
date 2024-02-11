package kr.goldenmine.baekjoon.platinum.platinum2.p2261;

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

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distanceSquare(Point other) {
            int xDiff = other.x - x;
            int yDiff = other.y - y;

            return xDiff * xDiff + yDiff * yDiff;
        }
    }

    //
    public static int getClosestPair(Point[] arr, int start, int finish) {
        int dots = finish - start + 1;
        if(dots == 3) {
            int min = Math.min(
                    arr[start].distanceSquare(arr[start + 1]),
                    arr[start + 1].distanceSquare(arr[start + 2]));
            min = Math.min(min, arr[start].distanceSquare(arr[start + 2]));

            return min;
        } else if(dots == 2) {
            return arr[start].distanceSquare(arr[start + 1]);
        }

        int mid = (start + finish) / 2;

        int left = getClosestPair(arr, start, mid);
        int right = getClosestPair(arr, mid + 1, finish);

        int min = Math.min(left, right);

//        int leftLimit = lowerBound(arr, arr[mid].x - min);
//        int rightLimit = upperBound(arr, arr[mid + 1].x + min) - 1;

        return Math.min(min, middleBand(arr, start, mid, finish, min));
    }

    static int middleBand(Point[] arr, int start, int mid, int end, int minDist) {
        int xDist;

        // index 참조가 많으므로 ArrayList를 활용
        ArrayList<Point> list = new ArrayList<>();

        // 후보군 추출하기
        int midX = arr[mid].x;	// mid인덱스의 x좌표값
        for (int i = start; i <= end; i++) {
            xDist = arr[i].x - midX;	// x좌표 차이

            if (xDist * xDist < minDist) {
                list.add(arr[i]);
            }
        }

        // 후보군들을 y좌표 기준으로 정렬
        Collections.sort(list, Ycomp);

        // 후보군들을 순회하면서 y좌표 차이가 midDist내에 있는 원소들만 거리 측정
        int yDist;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                yDist = list.get(i).y - list.get(j).y;
                if (yDist * yDist < minDist) {
                    minDist = Math.min(list.get(i).distanceSquare(list.get(j)), minDist);
                } else {
                    break;
                }
            }
        }
        return minDist;
    }

    // Y좌표를 오름차순으로 정렬하는 Comparator 익명객체
    static Comparator<Point> Ycomp = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.y - o2.y;
        }
    };


    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        Point[] points = new Point[N];

        for(int i = 0; i < N;i++) {
            points[i] = new Point(scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(points, Comparator.comparingInt(o -> o.x));

        int dist = getClosestPair(points, 0, N - 1);

        System.out.println(dist);
    }
}
