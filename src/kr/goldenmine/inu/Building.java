package kr.goldenmine.inu;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Building {
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

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class Writer extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;

        char[] bufForWriteNumber = new char[24];

        Writer() {
            this(1 << 10);
        }

        Writer(int size) {
            super(new OutputStreamWriter(System.out), size);
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 12 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 12; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeLong(long n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 22 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 22; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeString(String s) throws IOException {
            for(int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for(int i = off; i < off + len; i++) {
                write(cbuf[i]);
            }
        }

        public void write(int c) throws IOException {
            if (index >= nChars) flush();
            buf[index++] = (char) c;
        }

        public void newLine() throws IOException {
            write('\n');
        }

        @Override
        public void flush() throws IOException {
            super.write(buf, 0, index);
            index = 0;
            super.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point[] directions = {
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 0),
            new Point(-1, 0),
    };
    /*
6 7
0000000
0111000
0101000
0111000
0000000
0000000
4
2 5 6 7
4 4 5 5
3 3 3 3
3 3 3 4
     */

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int[][] map = new int[Y + 1][X + 1];
        for(int i = 1; i <= Y; i++) {
            String line = scan.nextString();
            for(int j = 1; j <= X; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 1));

        boolean[][] visited = new boolean[Y + 1][X + 1];
        visited[1][1] = true;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            map[p.y][p.x] = 2;

            for(Point d : directions) {
                Point next = new Point(p.x + d.x, p.y + d.y);
                if(next.x <= 0 || next.x > X || next.y <= 0 || next.y > Y) continue;
                if(visited[next.y][next.x]) continue;
                if(map[next.y][next.x] != 0) continue;

                visited[next.y][next.x] = true;

                queue.add(next);
            }
        }

        int[][] ranges = new int[Y + 1][X + 1];
        for(int i = 1; i <= Y; i++) {
            for(int j = 1; j <= X; j++) {
                int hasBuilding = (map[i][j] == 1 || map[i][j] == 0) ? 1 : 0;
                ranges[i][j] = ranges[i - 1][j] + ranges[i][j - 1] - ranges[i - 1][j - 1] + hasBuilding;
            }
        }

//        System.out.println();
//        for(int i = 0; i <= Y; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println();
//        for(int i = 0; i <= Y; i++) {
//            System.out.println(Arrays.toString(ranges[i]));
//        }

        Writer w = new Writer();
        // 여기부터 map에서 0 or 1이면 건물임
        int T = scan.nextInt();
        while(T-- > 0) {
            int Y1 = scan.nextInt();
            int X1 = scan.nextInt();
            int Y2 = scan.nextInt();
            int X2 = scan.nextInt();

            int count = ranges[Y2][X2] + ranges[Y1 - 1][X1 - 1] - ranges[Y2][X1 - 1] - ranges[Y1 - 1][X2];
            if(count == 0) {
                w.writeString("Yes\n");
            } else {
                w.writeString("No ");
                w.writeInt(count);
                w.write('\n');
            }
        }
        w.close();
    }
}
