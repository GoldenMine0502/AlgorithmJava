package kr.goldenmine.inu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class INUAdventure {
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

    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    static final int NO_INPUT = -1;
    static final int DOWN = 0;
    static final int STAY = 1;
    static final int UP = 2;

    static int parseEvent(String event) {
        if(event.equals("Down")) return 0;
        if(event.equals("Stay")) return 1;
        if(event.equals("Up")) return 2;
        return -1;
    }

    static int parseWASD(char wasd) {
        if(wasd == 'W') return 0;
        if(wasd == 'A') return 1;
        if(wasd == 'S') return 2;
        return 3;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int x = 0;
        int y = 0;

        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = scan.nextInt();
                if(map[i][j] == 2) {
                    x = j;
                    y = i;
                    map[i][j] = 0;
                }
            }
        }
        int[] wasd = {
                parseEvent(scan.nextString()),
                parseEvent(scan.nextString()),
                parseEvent(scan.nextString()),
                parseEvent(scan.nextString()),
        };
//        System.out.println(Arrays.toString(wasd));
        String keys = scan.nextString();

        int[] wasdState = {
                NO_INPUT,
                NO_INPUT,
                NO_INPUT,
                NO_INPUT,
        };

        for(int i = 0; i < M; i++) {
            final int idx = parseWASD(keys.charAt(i));

            // W, A, S, D 순서로 각 키가 이벤트 발생 조건을 만족하는지 확인합니다.
            for(int j = 0; j < 4; j++) {
                // 조건에 맞는 이벤트가 있다면 해당 이벤트를 발생시킵니다.
                if(j == idx) { // 누른 키 하나
                    if(wasdState[j] == NO_INPUT || wasdState[j] == UP) {
                        wasdState[j] = DOWN;
                    } else {
                        wasdState[j] = STAY;
                    }
                } else { // 안누른 나머지 키
                    if(wasdState[j] == DOWN || wasdState[j] == STAY) {
                        wasdState[j] = UP;
                    } else if(wasdState[j] == UP) {
                        wasdState[j] = NO_INPUT;
                    }
                }
            }

            // 발생한 이벤트가 키에 할당된 키 이벤트와 동일하면 횃불이가 이동합니다.
            for(int j = 0; j < 4; j++) {
                if(wasdState[j] == wasd[j]) {
                    if       (j == 0 && y > 0     && map[y - 1][x] != 1) { // W
                        y--;
                    } else if(j == 1 && x > 0     && map[y][x - 1] != 1) { // A
                        x--;
                    } else if(j == 2 && y < N - 1 && map[y + 1][x] != 1) { // S
                        y++;
                    } else if(j == 3 && x < N - 1 && map[y][x + 1] != 1){ // D
                        x++;
                    }
//                    System.out.println(y + " " + x);
                }
            }
//            System.out.println(keys.charAt(i) + ": " + Arrays.toString(wasdState));
//            System.out.println(i + ": " + (N - y - 1) + " " + x);
        }
//        System.out.println((N - y - 1) + " " + x);
        System.out.println((y + 1) + " " + (x + 1));
    }
}

/*
5 3
0 0 2 0 0
0 1 0 0 1
1 1 0 0 0
0 0 1 0 0
0 0 0 0 0
Down
Stay
Up
Stay
ASA
 */