package kr.goldenmine.baekjoon.silver.silver4.p2422;

import java.util.HashSet;
import java.util.Objects;

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

    static class Recipe {
        int a;
        int b;

        Recipe(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Recipe recipe = (Recipe) o;
            return a == recipe.a && b == recipe.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        int N = scan.nextInt();
        int M = scan.nextInt();

        HashSet<Recipe> recipes = new HashSet<>();
        for(int i = 0; i < M; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            recipes.add(new Recipe(a, b));
            recipes.add(new Recipe(b, a));
        }

        int count = 0;
        for(int i1 = 1; i1 <= N; i1++) {
            for(int i2 = i1 + 1; i2 <= N; i2++) {
                for(int i3 = i2 + 1; i3 <= N; i3++) {
                    if(i1 == i2 || i2 == i3 || i3 == i1) continue;

                    if(recipes.contains(new Recipe(i1, i2))) continue;
                    if(recipes.contains(new Recipe(i2, i3))) continue;
                    if(recipes.contains(new Recipe(i1, i3))) continue;

                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
