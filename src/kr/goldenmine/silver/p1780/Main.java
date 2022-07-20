package kr.goldenmine.silver.p1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                if(st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static int minus = 0;
    static int zero = 0;
    static int one = 0;

    public static void main(String[] args) {
        FastReader reader = new FastReader();

        int N = reader.nextInt();

        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = reader.nextInt();
            }
        }

        checkOne(arr, 0, 0, N, N);

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }

    public static void checkOne(int[][] arr, int startX, int startY, int finishX, int finishY) {
        int first = arr[startY][startX];

        boolean cut = false;

        for(int i = startY; i < finishY; i++) {
            for(int j = startX; j < finishX; j++) {
                if(first != arr[i][j]) {
                    cut = true;
                    break;
                }
            }

            if(cut) break;
        }

        if(cut) {
            int cutSize = (finishX - startX) / 3;

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    checkOne(arr, startX + j * (cutSize), startY + i * cutSize, startX + (j + 1) * (cutSize), startY + (i + 1) * cutSize);
                }
            }
        } else {
            if(first == -1) {
                minus++;
            }
            if(first == 0) {
                zero++;
            }
            if(first == 1) {
                one++;
            }
        }
    }
}
