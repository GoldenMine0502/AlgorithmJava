package kr.goldenmine.gold.gold4.p28037;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static class Point3D {
        double x;
        double y;
        double z;

        public Point3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
//            return String.format("%.8f %.8f %.8f", x, y, z);
            return x + " " + y + " " + z;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            // 1은 직교좌표계, 2는 원통좌표계, 3은 구면좌표계
            int from = scan.nextInt();
            int to = scan.nextInt();

            double a = scan.nextDouble();
            double b = scan.nextDouble();
            double c = scan.nextDouble();

            Point3D res = new Point3D(a, b, c);
            if(from == 2) {
                res = toRectangularFromCylindrical(res);
            } else if(from == 3) {
                res = toRectangularFromSpherical(res);
            }

            if(to == 2) {
                res = toCylindrical(res);
            } else if(to == 3) {
                res = toSpherical(res);
            }

            System.out.println(res);
        }
    }

    static final double EPSILON = 0.00000001D;

    public static Point3D toCylindrical(Point3D p3d) {
        double radius = Math.sqrt(p3d.x * p3d.x + p3d.y * p3d.y);
        // 각도 계산 (음수인 경우 보정)
        double angle = Math.atan2(p3d.y, p3d.x);  // 각도 계산

        if (angle < 0) {
            angle += 2 * Math.PI;  // 음수인 경우 보정
        }

        if(radius == 0) angle = 0;

        return new Point3D(radius, angle, p3d.z);
    }

    public static Point3D toSpherical(Point3D p3d) {
        double a = Math.sqrt(p3d.x * p3d.x + p3d.y * p3d.y);
        double radius = Math.sqrt(p3d.x * p3d.x + p3d.y * p3d.y + p3d.z * p3d.z);

        // 각도 계산 (음수인 경우 보정)
        double angle = Math.atan2(p3d.y, p3d.x);  // 각도 계산

        if (angle < 0) {
            angle += 2 * Math.PI;  // 음수인 경우 보정
        }

        double theta = Math.acos(p3d.z / radius);
        if(radius == 0) theta = 0;
        if(a == 0) angle = 0;

        return new Point3D(radius, theta, angle);
    }

    public static Point3D toRectangularFromCylindrical(Point3D p3d) {
        return new Point3D(p3d.x * Math.cos(p3d.y), p3d.x * Math.sin(p3d.y), p3d.z);
    }

    public static Point3D toRectangularFromSpherical(Point3D p3d) {
        return new Point3D(p3d.x * Math.sin(p3d.y) * Math.cos(p3d.z), p3d.x * Math.sin(p3d.y) * Math.sin(p3d.z), p3d.x * Math.cos(p3d.y));
    }
}
