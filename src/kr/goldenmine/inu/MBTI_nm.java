package kr.goldenmine.inu;

import java.util.ArrayList;
import java.util.List;

public class MBTI_nm {
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

    static String[] arr2; // arr2는 부동
    static List<String> current;
    static int max = 0;

    static int getDiff(String a, String b) {
        int c = 0;
        for(int i = 0; i < 4; i++) {
            if(a.charAt(i) != b.charAt(i)) c++;
        }
        return c;
    }

    static void check() {
        int sum = 0;

        for(int i = 0; i < arr2.length; i++) {
            sum += getDiff(current.get(i), arr2[i]);
        }

        max = Math.max(max, sum);
    }
    /*

5
INTP
ISFP
ISTJ
INFP
ENTP
ENTJ
INFJ
ENFP
INFJ
ESTJ

     */

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextString();
        }
        arr2 = new String[N];
        for(int i = 0; i < N; i++) {
            arr2[i] = scan.nextString();
        }

        boolean[] used = new boolean[N];
        current = new ArrayList<>();
        permute(arr, used);

        System.out.println(max);
    }

    static void permute(String[] arr, boolean[] used) {
        if (current.size() == arr.length) {
            check();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(arr[i]);
                permute(arr, used);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
}
