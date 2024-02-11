package kr.goldenmine.baekjoon.gold.gold4.p2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
090870000
704039810
800014796
407980001
000027964
000041087
000452670
672198000
540763120

000000000
000000000
000000000
000000000
000000000
000000000
000000000
000000000
000000000
     */
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

    static int[][] arr = new int[9][9];

    static int count = 0;

    static int solve(int X, int Y, int k) { // return 0 = 스도쿠 판 완성 -1 = 실패. 다시 되돌아감
        //cout << "solve(x=" << x << ", y=" << y << ", n=" << n << ")" << endl;
        for (int y = Y; y < 9; y++) {
            for (int x = (y == Y ? X : 0); x < 9; x++) {
                if (arr[y][x] == 0) {
                    for (int n = 1; n <= 9; n++) {
                        arr[y][x] = n;

//                        for (int j = 0; j < 9; j++) {
//                            for (int i = 0; i < 9; i++) {
//                                System.out.print(arr[j][i] + " ");
//                            }
//                            System.out.println();
//                        }
//
//                        try {
//                            Thread.sleep(1000L);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }

                        if (check(x, y)) {
                            count++;
                            if (solve(x, y, n) == 0) {
                                return 0;
                            } // -1 반환되면 다음 값 시도
                        }
                        arr[y][x] = 0;

                        if (n == 9) {
                            return -1; // 어떤 값도 시도하였으나 실패
                        }
                    }
                }
            }
        }

        return 0; // 반복문 끝까지 다 돌았으니 완료
    }

    static boolean check(int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (i != x && (arr[y][i] != 0 && arr[y][i] == arr[y][x])) {
                //cout << "1 " << x << ", " << y << ", " << i << ", " << y << ", " << arr[i][y] << ", " << arr[x][y] << endl;
                return false;
            }
            if (i != y && (arr[i][x] != 0 && arr[i][x] == arr[y][x])) {
                //cout << "2 " << x << ", " << y << ", " << x << ", " << i << ", " << arr[x][i] << ", " << arr[x][y] << endl;
                return false;
            }
        }

        int sectionX = (x / 3) * 3;
        int sectionY = (y / 3) * 3;

        for (int j = sectionY; j < sectionY + 3; j++) {
            for (int i = sectionX; i < sectionX + 3; i++) {
                if (i != x || j != y) {
                    if (arr[j][i] != 0 && arr[j][i] == arr[y][x]) return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        for (int j = 0; j < 9; j++) {
            String line = scan.nextLine();
            for (int i = 0; i < 9; i++) {
                arr[j][i] = line.charAt(i) - '0';
            }
        }

//        for (int j = 0; j < 9; j++) {
//            for (int i = 0; i < 9; i++) {
//                System.out.print(arr[j][i]);
//            }
//            System.out.println();
//        }

//        for (int j = 0; j < 9; j++) {
//            for (int i = 0; i < 9; i++) {
//                arr[j][i] = scan.nextInt();
//            }
//        }

        solve(0, 0, -1);

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                System.out.print(arr[j][i]);
            }
            System.out.println();
        }

        System.out.println(count);

//        for (int j = 0; j < 9; j++) {
//            for (int i = 0; i < 9; i++) {
//                System.out.print(arr[j][i] + " ");
//            }
//            System.out.println();
//        }
    }
}