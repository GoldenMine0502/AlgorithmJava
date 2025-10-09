package kr.goldenmine.baekjoon.gold.gold4.p14500;

import java.util.Arrays;
import java.util.List;

public class Main {
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

    // 4 by 4 가정
    static class Pattern {
        int[][] pattern;
        int ySize;
        int xSize;

        Pattern(String[] pattern, int ySize, int xSize) {
            this.pattern = new int[4][4];
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    this.pattern[i][j] = pattern[i].charAt(j) - '0';
                }
//                System.out.println(Arrays.toString(this.pattern[i]));
            }
//            System.out.println(ySize + ", " + xSize);
            this.ySize = ySize;
            this.xSize = xSize;
        }

        int check(int[][] map, int y, int x, int Y, int X) {
            if(y > Y - ySize) return 0;
            if(x > X - xSize) return 0;
//            System.out.println("A");
            int sum = 0;
            for(int i = 0; i < ySize; i++) {
                for(int j = 0; j < xSize; j++) {
                    if(this.pattern[i][j] == 1) {
                        sum += map[i + y][j + x];
                    }
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        List<Pattern> patterns = Arrays.asList(
                // 세로로 네개
                new Pattern(new String[] {
                        "1111",
                        "0000",
                        "0000",
                        "0000",
                }, 1, 4),
                new Pattern(new String[] {
                        "1000",
                        "1000",
                        "1000",
                        "1000",
                }, 4, 1),
                // 2 by 2
                new Pattern(new String[] {
                        "1100",
                        "1100",
                        "0000",
                        "0000",
                }, 2, 2),
                // ㄴ 모양
                new Pattern(new String[] {
                        "1000",
                        "1000",
                        "1100",
                        "0000",
                }, 3, 2),
                new Pattern(new String[] {
                        "0010",
                        "1110",
                        "0000",
                        "0000",
                }, 2, 3),
                new Pattern(new String[] {
                        "1100",
                        "0100",
                        "0100",
                        "0000",
                }, 3, 2),
                new Pattern(new String[] {
                        "1110",
                        "1000",
                        "0000",
                        "0000",
                },2 , 3),
                // ㄴ 뒤집기
                new Pattern(new String[] {
                        "0100",
                        "0100",
                        "1100",
                        "0000",
                },3 , 2),
                new Pattern(new String[] {
                        "1110",
                        "0010",
                        "0000",
                        "0000",
                },2 , 3),
                new Pattern(new String[] {
                        "1100",
                        "1000",
                        "1000",
                        "0000",
                }, 3, 2),
                new Pattern(new String[] {
                        "1000",
                        "1110",
                        "0000",
                        "0000",
                }, 2, 3),
                // Z 모양
                new Pattern(new String[] {
                        "1100",
                        "0110",
                        "0000",
                        "0000",
                }, 2, 3),
                new Pattern(new String[] {
                        "0100",
                        "1100",
                        "1000",
                        "0000",
                }, 3, 2),
                new Pattern(new String[] {
                        "0110",
                        "1100",
                        "0000",
                        "0000",
                }, 2, 3),
                new Pattern(new String[] {
                        "1000",
                        "1100",
                        "0100",
                        "0000",
                },3 , 2),
                // 빠~큐~
                new Pattern(new String[] {
                        "1000",
                        "1100",
                        "1000",
                        "0000",
                }, 3, 2),
                new Pattern(new String[] {
                        "0100",
                        "1110",
                        "0000",
                        "0000",
                }, 2, 3),
                new Pattern(new String[] {
                        "1110",
                        "0100",
                        "0000",
                        "0000",
                }, 2, 3),
                new Pattern(new String[] {
                        "0100",
                        "1100",
                        "0100",
                        "0000",
                }, 3, 2)
        );
//        System.out.println(patterns.size());

        Reader scan = new Reader();
        int Y = scan.nextInt();
        int X = scan.nextInt();

        int[][] map = new int[Y][X];
        for(int i = 0; i < Y; i++) {
            for(int j = 0; j < X; j++) {
               map[i][j] = scan.nextInt();
            }
        }

        int max = 0;
        for(int i = 0; i < Y; i++) {
            for(int j = 0; j < X; j++) {
                for(Pattern pattern : patterns) {
                    max = Math.max(max, pattern.check(map, i, j, Y, X));
                }
            }
        }
        System.out.println(max);
    }
}
