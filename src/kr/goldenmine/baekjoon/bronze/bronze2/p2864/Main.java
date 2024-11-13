package kr.goldenmine.baekjoon.bronze.bronze2.p2864;

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
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String s1 = scan.nextString();
        String s2 = scan.nextString();

        String s1_small = s1.replace("6", "5");
        String s1_big = s1.replace("5", "6");

        String s2_small = s2.replace("6", "5");
        String s2_big = s2.replace("5", "6");

        int min = Integer.parseInt(s1_small) + Integer.parseInt(s2_small);
        int max = Integer.parseInt(s1_big) + Integer.parseInt(s2_big);

        System.out.println(min + " " + max);
    }
}
