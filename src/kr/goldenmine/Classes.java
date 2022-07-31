package kr.goldenmine;

import kr.goldenmine.gold.gold2.p2021.Main;

public class Classes {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    static class DepthPoint {
        int x;
        int y;
        int d;

        public DepthPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public DepthPoint(int x, int y, int d) {
            this(x, y);
            this.d = d;
        }

        public DepthPoint add(DepthPoint p, int d) {
            return new DepthPoint(x + p.x, y + p.y, d);
        }
    }
}
