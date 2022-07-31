package kr.goldenmine.silver.silver2.p1780S;

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
    }

    // global minus = 0
    static int minus = 0;
    static int zero = 0;
    static int one = 0;

    public static void main(String[] args) {
        // 값 입력받기
        FastReader reader = new FastReader();

        int N = reader.nextInt();

        // 데이터 저장할 2차원배열
        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = reader.nextInt();
            }
        }

        // solve
        checkOne(arr, 0, 0, N, N);

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }

    // def checkOne(arr, startX, startY, finishX, finishY)
    public static void checkOne(int[][] arr, int startX, int startY, int finishX, int finishY) {
        int first = arr[startY][startX];

        // cut = 해당 구획이 다 똑같은 값이 아니라서 9등분 해야하는 경우
        boolean cut = false;

        for(int i = startY; i < finishY; i++) { // for i in range(startY, finishY):
            for(int j = startX; j < finishX; j++) { // for j in range(startX, finishX):
                if(first != arr[i][j]) { // if first != arr[i][j]:
                    cut = true;
                    break;
                }
            }

            if(cut) break;
        }

        if(cut) {
            // 9등분
            int cutSize = (finishX - startX) / 3;

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    checkOne(arr, startX + j * (cutSize), startY + i * cutSize, startX + (j + 1) * (cutSize), startY + (i + 1) * cutSize);
                }
            }
        } else {
            // 9등분 안하는거면 -1, 0, 1인지에 따라 값 더해주기, 전역변수
            if(first == -1) {
                minus++; // minus += 1
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
