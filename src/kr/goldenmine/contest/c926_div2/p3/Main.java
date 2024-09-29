package kr.goldenmine.contest.c926_div2.p3;

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

    static class Point {
        long currency;
        int previousDefeat;
        int initial;

        public Point(long currency, int previousDefeat, int initial) {
            this.currency = currency;
            this.previousDefeat = previousDefeat;
            this.initial = initial;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "currency=" + currency +
                    ", previousDefeat=" + previousDefeat +
                    ", initial=" + initial +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        while(T-- > 0) {
            int K = scan.nextInt();
            int X = scan.nextInt() + 1;
            int A = scan.nextInt();

            Queue<Point> queue = new LinkedList<>(); // 이전 패배와 다음 패배간의 갭에서 +가 나는가 체크
//            for(int i = 0; i < X; i++) {
                queue.add(new Point(A,0, 0));
//            }
             // 이전에 승리
//            queue.add(new Point(A, 1, 2)); // 이전에 패배

            int succeed = 0;

            while(!queue.isEmpty()) {
                Point p = queue.poll();

//                System.out.println(p);

                if(p.previousDefeat % X == 0 && p.initial >= X * 2) {
                    if (p.currency >= A) { // 최악의 사이클을 돌았는데 본전임?
//                        System.out.println("SUCCESS");
                        succeed++;
                    }
                } else {
                    if(p.currency > 0) {
                        if ((p.previousDefeat + 1) % X == 0) { // 현재 질 수 없는 상황. 나머지 상황은 졌다고 가정한다.
//                            System.out.println("WIN");
                            queue.add(new Point(p.currency + K - 1, 0, p.initial + 1)); // 이길땐 항상 가장 적은 이득을 본다.
                        } else { // 졌다. 무조건 1코인만 넣자.
//                            System.out.println("LOSE");
                            queue.add(new Point(p.currency - 1, p.previousDefeat + 1, p.initial + 1));
                        }
                    }
                }
            }
            System.out.println(succeed == 1 ? "YES" : "NO");
        }
    }
}
